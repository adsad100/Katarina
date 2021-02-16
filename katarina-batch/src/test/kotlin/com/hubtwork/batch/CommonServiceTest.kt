package com.hubtwork.batch

import com.hubtwork.BatchApplication
import com.hubtwork.riot.api.RiotAPI
import org.junit.Test
import org.assertj.core.api.Assertions
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles("dev")
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [BatchApplication::class] )
class CommonServiceTest {

    @Autowired
    lateinit var apiService: RiotAPI

    companion object {
        private val logger = LoggerFactory.getLogger(CommonServiceTest::class.java)
    }

    @Test
    fun CoreAPI_테스트() {

        Assertions.assertThat(apiService == null).isEqualTo(false)
        val summonerData = apiService.getSummonerByName("DopaL").block()
        logger.info(summonerData.toString())
        Assertions.assertThat(summonerData != null).isEqualTo(true)

    }
}