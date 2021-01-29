package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.NormalMatch
import com.hubtwork.katarina.batchmatch.api.repository.NormalMatchRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NormalMatchService(private val repository: NormalMatchRepository) {

    fun create(match: NormalMatch) =
        repository.save(match)

    fun read(matchId: Long): NormalMatch? {
        TODO("Not yet implemented")
        return repository.findByIdOrNull(matchId)
    }

    fun update() {
        TODO("Not yet implemented")
    }

    fun delete() {
        TODO("Not yet implemented")
    }
}