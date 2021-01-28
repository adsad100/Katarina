package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.SoloRank
import com.hubtwork.katarina.batchmatch.api.repository.SoloRankRepository
import org.springframework.data.repository.findByIdOrNull

class SoloRankService(val repository: SoloRankRepository) {

    fun create(match: SoloRank) {
        repository.save(match)
        TODO("Not yet implemented")
    }

    fun read(matchId: Long): SoloRank? {
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