package com.hubtwork.katarina.service

import com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner.SummonerDTO
import com.hubtwork.riot.api.RiotAPI
import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RiotServiceWrapper (private val riotAPI: RiotAPI) {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(RiotServiceWrapper::class.java)
    }

    fun getSummonerInfoForCheckFromAPI(accountId: String) : SummonerDTO {
        var summoner = riotAPI.getSummonerByAccountId(accountId).block()
        // while summoner from API is not null ( not 504, equals not empty Mono )
        // wait and callback for avoiding 504 Response
        while( summoner == null ) {
            logger.warn("Retry Request")
            Thread.sleep(1000L)
            summoner = riotAPI.getSummonerByAccountId(accountId).block()
        }
        return summoner
    }

    fun getMatchDataFromAPI(matchId: Long) : MatchDTO {
        var matchDetail = riotAPI.getMatchById(matchId).block()
        // matchDetail Retrying calls until Avoiding 504 Response.
        while (matchDetail == null) {
            logger.warn("Retry Request")
            Thread.sleep(1000L)
            matchDetail = riotAPI.getMatchById(matchId).block()
        }
        return matchDetail
    }

    fun getSummonerMatchListFromAPI(accountId: String): List<Long> {
        var matchList = mutableListOf<Long>()
        var beginIndex = 0
        while(true){
            var currentMatchList = riotAPI.getMatchListWithIndexRange100(accountId, beginIndex).block()
            // if error raised from api.
            if (currentMatchList != null) {
                val matches = currentMatchList.matches
                // if there's no match
                if ( matches.isEmpty() ) break
                else matches
                    .filter {
                        // Summoner's Rift
                        it.queue == 325                // ALL_RANDOM
                                || it.queue == 900      // ALL_RANDOM_URF
                                || it.queue == 1010     // ALL_RANDOM_URF_SNOW
                                || it.queue == 430      // BLIND_PICK
                                || it.queue == 400      // NORMAL DRAFT
                                || it.queue == 420      // RANK SOLO
                                || it.queue == 440      // RANK FLEX
                                || it.queue == 76       // URF
                                // ARAM
                                || it.queue == 450      // ARAM
                                || it.queue == 78       // ARAM OneForAll Mirror
                                || it.queue == 920      // ARAM PoroKing

                    }
                    .forEach {
                        matchList.add(it.gameId)
                    }
                beginIndex += 100
                // avoid api request call limit
                Thread.sleep(1000)
            }
            // If 504 Error occurred, wait and retry with kept indexes
            else {
                logger.warn("Retry Request")
                Thread.sleep(1000L)
            }
        }
        return matchList
    }

}