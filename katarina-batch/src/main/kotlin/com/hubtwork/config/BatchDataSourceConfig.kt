package com.hubtwork.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource


@Configuration
class BatchDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.batch")
    fun dataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()
        // UTF-8 :: 한글 문자열 처리
        dataSource.connectionInitSql = "SET NAMES utf8mb4"
        return dataSource
    }


}