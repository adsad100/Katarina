package com.hubtwork.katarina.batchmatch.service.batch

import com.hubtwork.katarina.batchmatch.api.domain.Summoner
import com.hubtwork.katarina.batchmatch.api.service.SummonerService
import com.hubtwork.katarina.batchmatch.api.service.UserWithMatchService
import com.hubtwork.katarina.batchmatch.service.riot.RiotAPI
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SummonerPipeline(private val riotAPI: RiotAPI,
                       private val summonerService: SummonerService,
                       private val userWithMatchService: UserWithMatchService,
                       private val matcherService: MatcherService
                       ) {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SummonerPipeline::class.java)
    }

    fun pipelining() {
        logger.info("-------- Get Summoners for Scan From DB ... --------")
        var summoners = getSummonersForScan()
        if( summoners.isEmpty() ) {
            logger.info("ERR :: There's no Summoner in DB.")
            return
        }
        logger.info(" GET :: ${ summoners.size } summoners. ")
        logger.info("-----------------------------------------------------")

        logger.info("------ Start Summoner - Match Scanning Process ------")
        summoners.map { summoner ->
            val pk = summoner.first
            val accountId = summoner.second
            logger.info("SCAN   :: [ $pk, $accountId ]")
            // check if this match enrolled already in DB
            val matchesAlreadyEnrolled = getSummonerMatchListInDB(accountId).map { it.matchId }.toSet()

            getSummonerMatchListFromAPI(accountId, matchesAlreadyEnrolled).forEach {
                getMatchDetailByAccountId(it)
                // avoid api request call limit
                Thread.sleep(1000)
            }
            checkSummonersScanned(pk)
        }
    }

    fun getSummonersForScan() : List<Pair<Int, String>> =
        summonerService.getSummonersForScan()

    fun getSummonerMatchListInDB(accountId: String) =
        userWithMatchService.getMatchListOfCurrentUser(accountId)

    fun getAllSummonerData() =
        summonerService.getAllSummonerEntry()

    fun getAllUserMatchDataCount(): Long =
        userWithMatchService.getAllDataCount()

    fun getAllSummonerDataCount(): Long =
        summonerService.getAllSummonerCount()

    // if match is not in DB , INSERT matches.
    fun getMatchDetailByAccountId(matchId: Long) {
        val matchDetail = riotAPI.getMatchById(matchId)!!.block()!!
        val queueType = matchDetail.queueId
        val matchEndTime = matchDetail.gameCreation + matchDetail.gameDuration * 1000

        val pipelinedMatch = matchDetail.pipeliningToMatch()
        matcherService.matchInserter(pipelinedMatch)

        matchDetail.participantIdentities.forEach {
            val player = it.player
            val accountId = player.accountId
            userWithMatchService.create(accountId, matchId, queueType, matchEndTime)
            if (summonerService.isSummonerExist(accountId) == 0)
                summonerService.create(Summoner(player.platformId, player.accountId, player.summonerName, player.summonerId))
        }
    }

    fun getSummonerMatchListFromAPI(accountId: String, alreadyScannedMatch: Set<Long>): List<Long> {
        var matchSet = mutableListOf<Long>()
        var beginIndex = 0
        while(true){
            val currentMatchList = riotAPI.getMatchListWithIndexRange100(accountId, beginIndex)
            // if error raised from api.
            if (currentMatchList != null) {
                val matches = currentMatchList.block()?.matches
                if (matches != null) {
                    // if there's no match
                    if ( matches.isEmpty() ) break
                    else matches.forEach {
                        matchSet.add(it.gameId)
                    }
                }
                beginIndex += 100
                // avoid api request call limit
                Thread.sleep(1000)
            }
            else break
        }
        return matchSet.minus(alreadyScannedMatch)
    }

    fun checkSummonersScanned(summonerId : Int) =
        summonerService.scannedSuccessful(summonerId)

}