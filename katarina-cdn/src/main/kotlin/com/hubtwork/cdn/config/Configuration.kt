package com.hubtwork.cdn.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

@Configuration
class Configuration(private val restTemplateBuilder: RestTemplateBuilder){

    companion object {
        var token: String = "RGAPI-511c4346-7d43-447f-910a-1637b5b99bca"

    }

    private fun createFactory() : ClientHttpRequestFactory {
        var factory = HttpComponentsClientHttpRequestFactory()
        factory.setConnectTimeout(2000)
        factory.setReadTimeout(2000)    // read timeout

        var httpClient = HttpClientBuilder.create()
            .setMaxConnPerRoute(50) // Connection pool on Route
            .setMaxConnTotal(300)   // Connection pool : Max Connection Pool
            // Idle Connection Shutdown on periodically, Killed unused connection on keep-alive time.
            .evictIdleConnections(2000L, TimeUnit.MILLISECONDS)
            .build()
        factory.httpClient = httpClient
        return factory
    }

    /**
     *          TODO - Setting for restTemplate ( HTTP Connections, etc...
     *
     */
    @Bean
    fun restTemplate(): RestTemplate {
        val restTemplate = restTemplateBuilder
            .defaultHeader("X-Riot-Token", token)
            .defaultHeader("Accept-Charset", "application/x-www-form-urlencoded; charset=UTF-8")
            .build()
        restTemplate.requestFactory = createFactory()
        return restTemplate
    }


    @Bean
    fun gson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()


}