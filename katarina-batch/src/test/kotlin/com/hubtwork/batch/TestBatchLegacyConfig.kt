package com.hubtwork.batch

import org.springframework.batch.core.Job
import org.springframework.batch.test.JobLauncherTestUtils

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing          // 배치 환경 로딩
class TestBatchLegacyConfig {

    @Autowired
    @Qualifier("loadMatchesJob")
    lateinit var loadMatchesJob: Job

    // 스프링 테스트 유틸
    @Bean
    fun jobLauncherTestUtils(): JobLauncherTestUtils {
        var util = JobLauncherTestUtils()
        return util
    }

    @Bean("loadMatchesJobTestConfig")
    fun loadMatchesJobTestConfig() : JobLauncherTestUtils {
        var util = JobLauncherTestUtils()
        util.job = loadMatchesJob
        return util
    }


}