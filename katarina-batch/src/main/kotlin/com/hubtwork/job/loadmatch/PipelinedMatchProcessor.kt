package com.hubtwork.job.loadmatch

import com.google.gson.Gson
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.PipelinedMatch
import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class PipelinedMatchProcessor (
    private val gson: Gson
): ItemProcessor<MatchList, PipelinedMatch>
{
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LoadMatchListProcessor::class.java)
    }

    override fun process(item: MatchList): PipelinedMatch {
        val matchData = gson.fromJson(item.matchData, MatchDTO::class.java)
        val matchId = matchData.gameId
        logger.info("---- Get MatchData from DB [ matchId : ${matchData.gameId} ]")
        val season = matchData.seasonId
        val queueType = matchData.queueId
        val matchJSON = gson.toJson(matchData.pipeliningToMatch())
        val matchEndTime = matchData.gameCreation + matchData.gameDuration * 1000
        logger.info("---- Pipelining Match Completed. ")
        return PipelinedMatch(matchId, season, queueType, matchEndTime, matchJSON)
    }

}