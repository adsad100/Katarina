package com.hubtwork.job.loadmatch

import com.hubtwork.jpa.domain.Summoner
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class SummonerMarkingProcessor
    : ItemProcessor<Summoner, Summoner>
{

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MatchMarkingProcessor::class.java)
    }

    override fun process(item: Summoner): Summoner {
        logger.info("Summoner Scanning Start [ accountId : {} ]", item.accountId)
        item.lastScanTime = LocalDateTime.ofInstant(Date().toInstant(), ZoneId.of("Asia/Seoul") )
        return item
    }

}