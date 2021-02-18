package com.hubtwork

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess

@EnableBatchProcessing      // Enable Batch
@SpringBootApplication
class BatchApplication

fun main(args: Array<String>) {
    var exitCode: Int = 0
    exitCode = try {
        SpringApplication.exit(runApplication<BatchApplication>(*args))
    } catch(e: Exception) {
        5
    }
    exitProcess(exitCode)
}
