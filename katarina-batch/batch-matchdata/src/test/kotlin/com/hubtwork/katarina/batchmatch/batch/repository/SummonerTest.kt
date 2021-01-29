package com.hubtwork.katarina.batchmatch.batch.repository

import com.hubtwork.katarina.batchmatch.api.domain.Summoner
import com.hubtwork.katarina.batchmatch.api.service.SummonerService
import com.hubtwork.katarina.batchmatch.batch.work.SummonerPipelineTest
import com.hubtwork.katarina.batchmatch.service.batch.SummonerPipeline
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import javax.transaction.Transactional

@SpringBootTest
class SummonerTest {

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(SummonerTest::class.java)
    }


    @Autowired
    lateinit var summonerService: SummonerService

    @Test
    @Transactional
    fun createTest() {

    }
}