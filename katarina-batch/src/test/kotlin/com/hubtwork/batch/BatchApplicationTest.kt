package com.hubtwork.batch

import com.hubtwork.BatchApplication
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.Summoner
import com.hubtwork.jpa.repository.MatchListRepository
import com.hubtwork.jpa.repository.PipelinedMatchRepository
import com.hubtwork.jpa.repository.SummonerRepository
import com.hubtwork.jpa.repository.UserWithMatchRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles("prod")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BatchApplication::class] )
class BatchApplicationTest {
    @Autowired
    lateinit var jobLauncherTestUtils: JobLauncherTestUtils
    @Autowired
    lateinit var summonerRepository: SummonerRepository
    @Autowired
    lateinit var matchListRepository: MatchListRepository
    @Autowired
    lateinit var userWithMatchRepository: UserWithMatchRepository
    @Autowired
    lateinit var pipelinedMatchRepository: PipelinedMatchRepository

    companion object {
        private val logger = LoggerFactory.getLogger(BatchApplicationTest::class.java)
    }

    //@AfterEach
    fun tearDown() {
        summonerRepository.deleteAllInBatch()
        matchListRepository.deleteAllInBatch()
        userWithMatchRepository.deleteAllInBatch()
        pipelinedMatchRepository.deleteAllInBatch()
    }

    @Test
    fun DataInsert() {
        // 이로현
        summonerRepository.save(Summoner("KR", "oHhwdX9u9vepY-rp4Qf4pXuc0Pf0EZTEGdWLxUicdhnT","9na_jptY8QGyAWTxRWPzxI_FEyTgI51RvmvCothbDxXBoA"))
        // DopaL
        summonerRepository.save(Summoner("KR", "kLnPMSdc4Gd1wbYJ1N5zoxCiPf_w2ys_8vkLhHMu4aeH","oMVgzTeKEmjW8KQzRqgbBv7jHS-LJc67Q2ICslpuUSWSrQ"))
        // 부산대표간지남
        summonerRepository.save(Summoner("KR", "GaU-e7_Trx3Wq_dlAai7cxJBISAIzQsehcSxtSg2yIY","1egTDW2rtjWsGNWxi0kzLf-K3fX09JBWBhJT32aAcoLedw"))
    }

    @Test
    fun JPA_만약_DB에_데이터가_없다면() {
        matchListRepository.save(MatchList(12341, false))
        val matchesNotScan = matchListRepository.countAllMatchesIsNotScanned().toInt()
        var match = matchListRepository.getOne(12341)
        match.matchData = "r1r"
        matchListRepository.save(match)
        val matchesNotYet = matchListRepository.countAllMatchesIsNotMappedFromAPI().toInt()
        Assertions.assertThat(matchesNotYet).isEqualTo(0)
        Assertions.assertThat(matchesNotScan).isEqualTo(1)
    }

    @Test
    fun DB에서_소환사정보_가져와서_매치정보_수집_통합테스트() {
        // 이로현
        summonerRepository.save(Summoner("KR", "oHhwdX9u9vepY-rp4Qf4pXuc0Pf0EZTEGdWLxUicdhnT","9na_jptY8QGyAWTxRWPzxI_FEyTgI51RvmvCothbDxXBoA"))
        // DopaL
        summonerRepository.save(Summoner("KR", "kLnPMSdc4Gd1wbYJ1N5zoxCiPf_w2ys_8vkLhHMu4aeH","oMVgzTeKEmjW8KQzRqgbBv7jHS-LJc67Q2ICslpuUSWSrQ"))
        // 부산대표간지남
        summonerRepository.save(Summoner("KR", "GaU-e7_Trx3Wq_dlAai7cxJBISAIzQsehcSxtSg2yIY","1egTDW2rtjWsGNWxi0kzLf-K3fX09JBWBhJT32aAcoLedw"))

        val jobExecution = jobLauncherTestUtils.launchJob()
        Assertions.assertThat(jobExecution.status).isEqualTo(BatchStatus.COMPLETED)

        val allMatchCount = matchListRepository.countAllMatches().toInt()
        val allMatchCountDistinct = matchListRepository.countAllMatchesDistinct().toInt()
        val justEnrolledMatchCount = matchListRepository.countAllMatchesJustEnrolledFromAPI().toInt()
        val mappedNotYetMatchCount = matchListRepository.countAllMatchesIsNotMappedFromAPI().toInt()
        val scannedNotYetMatchCount = matchListRepository.countAllMatchesIsNotScanned().toInt()
        logger.info("           Gathered Match")
        logger.info("[ Match Size : $allMatchCount ]    [ Match Distinct : $allMatchCountDistinct ]")
        logger.info("[ justEnrolled : $justEnrolledMatchCount ]")
        logger.info("[ mappedNotYet : $mappedNotYetMatchCount ]")
        logger.info("[ scannedNotYet : $scannedNotYetMatchCount ]")

        val pipelinedMatchCount = pipelinedMatchRepository.countAllMatches().toInt()
        val countAllRandom = pipelinedMatchRepository.countAllMatchesByQueueType(325).toInt()
        val countAllRandomURF = pipelinedMatchRepository.countAllMatchesByQueueType(900).toInt()
        val countAllRandomURFSnow = pipelinedMatchRepository.countAllMatchesByQueueType(1010).toInt()
        val countBlindPick = pipelinedMatchRepository.countAllMatchesByQueueType(430).toInt()
        val countNormalDraft = pipelinedMatchRepository.countAllMatchesByQueueType(400).toInt()
        val countRankSolo = pipelinedMatchRepository.countAllMatchesByQueueType(420).toInt()
        val countRankFlex = pipelinedMatchRepository.countAllMatchesByQueueType(440).toInt()
        val countURF = pipelinedMatchRepository.countAllMatchesByQueueType(476).toInt()
        val countARAM = pipelinedMatchRepository.countAllMatchesByQueueType(450).toInt()

        logger.info("           Match by QueueType")
        logger.info("[ allMatch : $pipelinedMatchCount ]")
        logger.info("[ AllRandom : $countAllRandom ]")
        logger.info("[ AllRandomURF : $countAllRandomURF ]")
        logger.info("[ AllRandomURFSnow : $countAllRandomURFSnow ]")
        logger.info("[ BlindPick : $countBlindPick ]")
        logger.info("[ NormalDraft : $countNormalDraft ]")
        logger.info("[ RankSolo : $countRankSolo ]")
        logger.info("[ RankFlex : $countRankFlex ]")
        logger.info("[ URF : $countURF ]")
        logger.info("[ ARAM : $countARAM ]")

        val summonerCount = summonerRepository.countAllSummoners().toInt()
        val summonerCountDistinct = summonerRepository.countAllSummonersDistinct().toInt()
        logger.info("           Gathered Summoner")
        logger.info("[ Summoner Size : $summonerCount ]    [ Summoner Distinct : $summonerCountDistinct ]")

        val userMatchCount = userWithMatchRepository.countAllMatchesWithUser().toInt()
        val userMatchCountDistinct = userWithMatchRepository.countAllMatchesWithUserDistinct().toInt()
        logger.info("           Gathered UserWithMatch")
        logger.info("[ UserWithMatch Size : $userMatchCount ]    [ UserWithMatch Distinct : $userMatchCountDistinct ]")

        summonerRepository.findAll().filter { it.lastScanTime.year == 2021 }.forEach {
            println("[ ${it.accountId} ] >>> ${it.lastScanTime}")
        }

        // 스캔 마킹이 전부 되었는지 ? ( 없어야 함 )
        Assertions.assertThat(scannedNotYetMatchCount).isEqualTo(0)
        // 매치데이터가 전부 매핑되었는지 ? ( 없어야 함 )
        Assertions.assertThat(mappedNotYetMatchCount).isEqualTo(0)
        // 매치데이터에 Id 만 할당된게 있는지 ? ( 없어야 함 )
        Assertions.assertThat(justEnrolledMatchCount).isEqualTo(0)
        // 매치리스트 중복제거가 제대로 되었는지 ?
        Assertions.assertThat(allMatchCount).isEqualTo(allMatchCountDistinct)
        // 소환사 중복제거가 제대로 되었는지 ?
        Assertions.assertThat(summonerCount).isEqualTo(summonerCountDistinct)
        // 매치당 소환사 매핑이 제대로 되었는지 ? [ 비율 1 : 10 ]
        Assertions.assertThat(allMatchCount).isEqualTo(userMatchCount / 10)
    }


}