package com.hubtwork.job.loadmatch

import com.hubtwork.jpa.domain.MatchList
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class MatchMarkingProcessor
    : ItemProcessor<MatchList, MatchList>
{

    companion object {
    private val logger: Logger = LoggerFactory.getLogger(MatchMarkingProcessor::class.java)
    }

    override fun process(item: MatchList): MatchList? {
        logger.info("MatchData Formatting completed [ matchId : {} ]", item.matchId)
        item.isScanned = true
        logger.info("----- MatchData Inserted ( ${item.isScanned} )")
        return item
    }

}