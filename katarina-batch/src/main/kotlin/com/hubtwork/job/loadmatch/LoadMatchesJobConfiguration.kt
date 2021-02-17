package com.hubtwork.job.loadmatch

import com.google.gson.Gson
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.PipelinedMatch
import com.hubtwork.jpa.domain.Summoner
import com.hubtwork.jpa.domain.UserWithMatch
import com.hubtwork.jpa.repository.MatchListRepository
import com.hubtwork.jpa.repository.SummonerRepository
import com.hubtwork.katarina.batchmatch.batch.util.ModifyingJpaPagingItemReader
import com.hubtwork.katarina.batchmatch.batch.util.DataShareBean
import com.hubtwork.katarina.batchmatch.batch.util.JpaListItemWriter
import com.hubtwork.katarina.service.RiotServiceWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.persistence.EntityManagerFactory

@Configuration
@EnableBatchProcessing
class LoadMatchesJobConfiguration (
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory,
    private val coreService: RiotServiceWrapper,
    private val summonerRepository: SummonerRepository,
    private val matchListRepository: MatchListRepository,
    private val gson: Gson
){


    /**
     *      *  스텝 1
     *      - Reader : DB에서 소환사 100명 가져옴
     *      - Processor : API 쏴서 matchId < Long > list 를 가져옴
     *          <List>
     *      - Writer : MatchList 에 matchId, Flag(False)
     *                 Summoner 에 isScanned 최신화
     *
     *      * 스텝 2
     *      - Reader : DB에서 matchData(null) 인 MatchList 가져옴
     *      - Processor : API 쏴서 matchData 가져옴
     *          <Single>
     *      - Writer : MatchList 에 matchId, matchData, Flag(False)
     *
     *      * 스텝 3
     *      - Reader : DB에서 Flag(False)인 매치 MatchList 가져옴
     *      - Processor : matchData Json 컨버팅 후 UserWithMatch 매핑
     *          <List>
     *      - Writer : UswerWithMatch 에 저장
     *
     *      * 스텝 4
     *      - Reader : DB에서 Flag(False)인 매치 MatchList 가져옴
     *      - Processor : matchData Json 컨버팅 후 Summoner 매핑
     *      - Writer : Summoner 에 저장
     *
     *      * 스텝 5
     *      - Reader : DB에서 Flag(False)인 매치 MatchList 가져옴
     *      - Processor : flag > True
     *      - Writer : MatchList
     *
     */

    companion object {
        const val summonerChunk = 10
        const val matcherChunk = 1000
        private val logger: Logger = LoggerFactory.getLogger(LoadMatchesJobConfiguration::class.java)
    }

    @Primary
    @Bean("loadMatchesJob")
    fun loadMatchesJob(): Job =
        jobBuilderFactory.get("loadMatches")
            .start(stepGetMatchesFromAPI())         // DB 소환사 정보 가져와 매치리스트 API
            .next(stepCheckSummonersAlreadyScanned())   // 소환사 정보 스캔타임 기록
            .next(stepGetMatchDetailFromAPI())      // 매치리스트 에서 매치 상세 API
            .next(stepExtractMatchData())           // 매치 데이터 파이프라이닝 및 분류 해 저장
            .next(stepExtractUserWithMatches())     // 매치상세 에서 accountId - MatchId 쌍 추출
            .next(stepExtractSummonerFromMatches()) // 매치상세 에서 연관 소환사 정보 추출
            .next(stepCheckMatchesAlreadyScanned()) // 매치정보 스캔 완료 마킹
            .build()

    @Bean
    fun stepGetMatchesFromAPI() : Step =
        stepBuilderFactory.get("Step_MatchesFromAPI")
            .chunk<Summoner, List<MatchList>>(summonerChunk)
            .reader(summonerFromDBItemReader())
            .processor(matchScanProcessor())
            .writer(matchListBulkItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepCheckSummonersAlreadyScanned() : Step =
        stepBuilderFactory.get("Step_CheckSummonersAlreadyScanned")
            .chunk<Summoner, Summoner>(summonerChunk)
            .reader(summonerForCheckItemReader())
            .processor(summonerMarkingProcessor())
            .writer(summonerItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepGetMatchDetailFromAPI() : Step =
        stepBuilderFactory.get("Step_GetMatchDetailFromAPI")
            .chunk<MatchList, MatchList>(matcherChunk)
            .reader(matchListForAPIItemReader())
            .processor(getMatchDetailItemProcessor())
            .writer(matchListItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepExtractMatchData() : Step =
        stepBuilderFactory.get("Step_ExtractMatchData")
            .chunk<MatchList, PipelinedMatch>(matcherChunk)
            .reader(matchListItemReader())
            .processor(extractPipelinedMatchProcessor())
            .writer(pipelinedMatchItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepExtractUserWithMatches() : Step =
        stepBuilderFactory.get("Step_ExtractUserWithMatches")
            .chunk<MatchList, List<UserWithMatch>>(matcherChunk)
            .reader(matchListItemReader())
            .processor(extractUserWithMatchProcessor())
            .writer(userWithMatchItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepExtractSummonerFromMatches() : Step =
        stepBuilderFactory.get("Step_ExtractSummonerFromMatch")
            .chunk<MatchList, List<Summoner>>(matcherChunk)
            .reader(matchListItemReader())
            .processor(extractSummonerFromMatchProcessor())
            .writer(summonerListItemWriter())
            .faultTolerant()
            .build()

    @Bean
    fun stepCheckMatchesAlreadyScanned() : Step =
        stepBuilderFactory.get("Step_CheckMatchesAlreadyScanned")
            .chunk<MatchList, MatchList>(matcherChunk)
            .reader(matchListForScanItemReader())
            .processor(markingMatchesIsScannedProcessor())
            .writer(matchListItemWriter())
            .faultTolerant()
            .build()

    private fun summonerFromDBItemReader() : JpaPagingItemReader<Summoner> {
        var itemReader = JpaPagingItemReader<Summoner>()
        itemReader.setEntityManagerFactory(entityManagerFactory)
        itemReader.setQueryString("select s from Summoner s order by last_scanned, accountId")
        itemReader.pageSize = summonerChunk
        itemReader.setMaxItemCount(10)
        return itemReader
    }

    private fun summonerForCheckItemReader() : ModifyingJpaPagingItemReader<Summoner> {
        var itemReader = ModifyingJpaPagingItemReader<Summoner>()
        itemReader.setEntityManagerFactory(entityManagerFactory)
        itemReader.setQueryString("select s from Summoner s order by last_scanned, accountId")
        itemReader.pageSize = summonerChunk
        itemReader.setMaxItemCount(10)
        return itemReader
    }

    private fun matchListForAPIItemReader() : ModifyingJpaPagingItemReader<MatchList> {
        var itemReader = ModifyingJpaPagingItemReader<MatchList>()
        itemReader.setEntityManagerFactory(entityManagerFactory)
        itemReader.setQueryString("select m from MatchList m where matchData = null and isScanned = 0 order by matchId")
        itemReader.pageSize = matcherChunk
        return itemReader
    }

    private fun matchListForScanItemReader() : ModifyingJpaPagingItemReader<MatchList> {
        var itemReader = ModifyingJpaPagingItemReader<MatchList>()
        itemReader.setEntityManagerFactory(entityManagerFactory)
        itemReader.setQueryString("select m from MatchList m where matchData != null and isScanned = 0 order by matchId")
        itemReader.pageSize = matcherChunk
        return itemReader
    }

    private fun matchListItemReader() : JpaPagingItemReader<MatchList> {
        var itemReader = JpaPagingItemReader<MatchList>()
        itemReader.setEntityManagerFactory(entityManagerFactory)
        itemReader.setQueryString("select m from MatchList m where matchData != null and isScanned = 0 order by matchId")
        itemReader.pageSize = matcherChunk
        return itemReader
    }

    private fun matchScanProcessor() = LoadMatchListProcessor(coreService, matchListRepository)

    private fun getMatchDetailItemProcessor() = GetMatchDetailProcessor(coreService, gson)

    private fun summonerMarkingProcessor() = SummonerMarkingProcessor()

    private fun extractPipelinedMatchProcessor() = PipelinedMatchProcessor(gson)

    private fun extractUserWithMatchProcessor() = MatchWithAccountProcessor(gson)

    private fun extractSummonerFromMatchProcessor() = SummonerFromMatchProcessor(gson, summonerRepository)

    private fun markingMatchesIsScannedProcessor() = MatchMarkingProcessor()

    private fun matchListBulkItemWriter() : JpaListItemWriter<MatchList> {
        val jpaItemWriter: JpaItemWriter<MatchList> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return JpaListItemWriter(jpaItemWriter)
    }

    private fun pipelinedMatchItemWriter() : JpaItemWriter<PipelinedMatch> {
        val jpaItemWriter : JpaItemWriter<PipelinedMatch> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return jpaItemWriter
    }
    private fun userWithMatchItemWriter() : JpaListItemWriter<UserWithMatch> {
        val jpaItemWriter: JpaItemWriter<UserWithMatch> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return JpaListItemWriter(jpaItemWriter)
    }

    private fun summonerListItemWriter() : JpaListItemWriter<Summoner> {
        val jpaItemWriter: JpaItemWriter<Summoner> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return JpaListItemWriter(jpaItemWriter)
    }

    private fun summonerItemWriter() : JpaItemWriter<Summoner> {
        val jpaItemWriter: JpaItemWriter<Summoner> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return jpaItemWriter
    }

    private fun matchListItemWriter() : JpaItemWriter<MatchList> {
        val jpaItemWriter: JpaItemWriter<MatchList> = JpaItemWriter()
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory)
        return jpaItemWriter
    }



}