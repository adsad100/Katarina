package com.hubtwork.job.loadmatch

import com.google.gson.Gson
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.jpa.domain.Summoner
import com.hubtwork.jpa.repository.SummonerRepository
import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class SummonerFromMatchProcessor(
    private val gson: Gson,
    private val repository: SummonerRepository
) : ItemProcessor<MatchList, List<Summoner>>
{

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(LoadMatchListProcessor::class.java)
    }

    override fun process(item: MatchList): List<Summoner> {
        var resultSet = mutableListOf<Summoner>()
        val matchData = gson.fromJson(item.matchData, MatchDTO::class.java)
        logger.info("---- Get MatchData from DB [ matchId : ${matchData.gameId} ]")
        matchData.participantIdentities.forEach {
            if (!repository.existsById(it.player.accountId)) {
                val player = it.player
                val accountId = player.accountId
                val platform = player.platformId
                val summonerId = player.summonerId
                resultSet.add(Summoner(platform, accountId, summonerId))
            }
        }
        logger.info("---- Summoners from Match Mapping Completed. ")
        return resultSet
    }

}