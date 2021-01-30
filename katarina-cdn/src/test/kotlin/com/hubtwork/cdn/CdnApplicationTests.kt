package com.hubtwork.cdn

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.RestTemplate

@SpringBootTest
class CdnApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    lateinit var restTemplate: RestTemplate
}
