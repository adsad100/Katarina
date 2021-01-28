package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.ARAM
import com.hubtwork.katarina.batchmatch.api.repository.ARAMRepository
import org.springframework.data.repository.findByIdOrNull

class ARAMService(private val repository: ARAMRepository) {

    fun create(match: ARAM) {
        repository.save(match)
        TODO("Not yet implemented")
    }

    fun read(matchId: Long): ARAM? {
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