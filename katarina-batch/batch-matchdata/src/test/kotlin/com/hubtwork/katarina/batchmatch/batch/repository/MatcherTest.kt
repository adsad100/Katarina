package com.hubtwork.katarina.batchmatch.batch.repository

import com.hubtwork.katarina.batchmatch.service.batch.MatcherService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MatcherTest {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MatcherTest::class.java)
    }

    @Autowired
    lateinit var matcherService: MatcherService



}