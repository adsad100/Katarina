package com.hubtwork.job.loadmatch

import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.Summoner
import com.hubtwork.jpa.repository.MatchListRepository
import com.hubtwork.katarina.service.RiotServiceWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

/**
*  스텝 3        [ 매치 돌면서 매치디테일 가져옴 ]
*      - Reader : matchId 중 matchId 만 On 인거
*      - Processor : 매치디테일 처리 ( Api Call )
*      - Writer : UserWithMatch 저장 ( Upsert )
*                 Match Detail 저장
*/
class LoadMatchListProcessor (
    private val coreService: RiotServiceWrapper,
    private val repository: MatchListRepository
) : ItemProcessor<Summoner, List<MatchList>>
{

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LoadMatchListProcessor::class.java)
    }

    override fun process(item: Summoner): List<MatchList> {
        // Avoid Too many request
        Thread.sleep(1000L)
        var resultSet = mutableListOf<MatchList>()
        val accountId = item.accountId
        logger.info("---- Request API to get match list By AccountId [ $accountId ] ")

        val matchList = coreService.getSummonerMatchListFromAPI(accountId)
        matchList.map {
            if (!repository.existsById(it)) {
                val data = MatchList(it, false)
                resultSet.add(data)
            }
        }
        logger.info("---- Total [ ${matchList.size} ] matches Loaded From API ")
        return resultSet
    }

}