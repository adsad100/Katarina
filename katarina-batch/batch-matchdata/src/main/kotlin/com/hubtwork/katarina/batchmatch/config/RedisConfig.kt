package com.hubtwork.katarina.batchmatch.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.serializer.StringRedisSerializer

import org.springframework.data.redis.core.StringRedisTemplate

import org.springframework.data.redis.core.RedisTemplate

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate

import java.awt.Color

import org.springframework.data.redis.serializer.RedisSerializationContext

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory

import org.springframework.data.redis.core.ReactiveRedisOperations


@Configuration
@EnableCaching
@Profile("dev")
class RedisConfig {
    @Bean
    fun redisOperations(factory: ReactiveRedisConnectionFactory?): ReactiveRedisOperations<String, Color>? {
        val serializer = Jackson2JsonRedisSerializer(Color::class.java)
        val builder = RedisSerializationContext.newSerializationContext<String, Color>(StringRedisSerializer())
        val context = builder.value(serializer).build()
        return ReactiveRedisTemplate(factory!!, context)
    }
}