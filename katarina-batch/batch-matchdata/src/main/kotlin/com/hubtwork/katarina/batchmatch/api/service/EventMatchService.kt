package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.EventMatch
import com.hubtwork.katarina.batchmatch.api.repository.EventMatchRepository
import org.springframework.data.repository.findByIdOrNull

class EventMatchService(private val repository: EventMatchRepository) {

    fun create(match: EventMatch) {
        repository.save(match)
        TODO("Not yet implemented")
    }

    fun read(matchId: Long): Any? {

        return repository.findByIdOrNull(matchId)
        TODO("Not yet implemented")
    }

    fun update() {
        TODO("Not yet implemented")
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}