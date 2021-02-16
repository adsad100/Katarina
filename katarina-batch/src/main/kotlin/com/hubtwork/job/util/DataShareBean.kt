package com.hubtwork.katarina.batchmatch.batch.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DataShareBean<T> {

    private val sharedData: MutableList<T>?

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DataShareBean::class.java)
    }

    init {
        sharedData = mutableListOf()
    }

    val size: () -> Int = {
        if (sharedData == null) {
            logger.error("sharedData initialized not yet")
            0
        }
        else sharedData.size
    }

    fun putData(data: T) {
        if (sharedData == null) {
            logger.error("sharedData initialized not yet")
            return
        }
        sharedData.add(data)
    }

    fun checkIfExist(data: T) : Boolean {
        if (sharedData == null) {
            logger.error("sharedData initialized not yet")
            return false
        }
        return sharedData.any { it == data }
    }

    fun getData() : List<T>? {
        if (sharedData == null) {
            logger.error("sharedData initialized not yet")
            return null
        }
        return sharedData
    }


}