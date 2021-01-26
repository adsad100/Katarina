package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.MatchData
import com.hubtwork.katarina.batchmatch.api.repository.MatchDataRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MatchDataService(val repository: MatchDataRepository) {

    @Transactional
    fun create(match: MatchData) {
        repository.save(match)
    }

    @Transactional(readOnly = true)
    fun read(id: Long): MatchData? {
        // TODO - Exception
        return repository.findByIdOrNull(id)
    }

    fun update() {

    }

    fun delete() {

    }

}
