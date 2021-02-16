package com.hubtwork.job.loadmatch

import com.google.gson.Gson
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.UserWithMatch
import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class MatchWithAccountProcessor(
    private val gson: Gson
): ItemProcessor<MatchList, List<UserWithMatch>>
{
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LoadMatchListProcessor::class.java)
    }

    override fun process(item: MatchList): List<UserWithMatch> {
        var resultSet = mutableListOf<UserWithMatch>()
        val matchData = gson.fromJson(item.matchData, MatchDTO::class.java)
        val matchId = matchData.gameId
        val queueType = matchData.queueId
        val matchEndTime = matchData.gameCreation + matchData.gameDuration * 1000
        logger.info("---- Get MatchData from DB [ matchId : ${matchData.gameId} ]")
        matchData.participantIdentities.forEach {
            val player = it.player
            val accountId = player.accountId
            resultSet.add(UserWithMatch(accountId, matchId, queueType, matchEndTime))
        }
        logger.info("---- User/Match Mapping Completed. ")
        return resultSet
    }

}