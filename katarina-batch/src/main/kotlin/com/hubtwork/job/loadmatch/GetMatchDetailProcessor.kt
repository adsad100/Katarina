package com.hubtwork.job.loadmatch

import com.google.gson.Gson
import com.hubtwork.jpa.domain.MatchList
import com.hubtwork.katarina.service.RiotServiceWrapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class GetMatchDetailProcessor (
    private val coreService: RiotServiceWrapper,
    private val gson: Gson
        ): ItemProcessor<MatchList, MatchList>
{
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(GetMatchDetailProcessor::class.java)
    }

    override fun process(item: MatchList): MatchList? {
        // Avoid Too many request
        Thread.sleep(1000L)
        logger.info("Get MatchData from API [ matchId : {} ]", item.matchId)
        val matchData = coreService.getMatchDataFromAPI(item.matchId)
        val matchString = gson.toJson(matchData)
        item.matchData = matchString
        logger.info("----- MatchData Inserted")
        return item
    }

}