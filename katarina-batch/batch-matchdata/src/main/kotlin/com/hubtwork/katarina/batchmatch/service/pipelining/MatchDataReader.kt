package com.hubtwork.katarina.batchmatch.service.pipelining

import com.hubtwork.katarina.batchmatch.service.riot.RiotAPI
import com.hubtwork.katarina.batchmatch.domain.katarina.matchlist.KatarinaMatchDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemReader

class MatchDataReader(private val riotAPI: RiotAPI)
    : ItemReader<List<KatarinaMatchDTO>>

{

    private val logger: Logger = LoggerFactory.getLogger(MatchDataReader::class.java)

    override fun read(): List<KatarinaMatchDTO>? {


        TODO("Not yet implemented")
    }




}