package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.interfaces.MatchInterface
import com.hubtwork.katarina.batchmatch.api.domain.MatchData
import com.hubtwork.katarina.batchmatch.api.repository.NormalMatchRepository

class NormalMatchService(private val repository: NormalMatchRepository) :MatchInterface{
    override fun create(match: MatchData) {
        TODO("Not yet implemented")
    }

    override fun read(id: Long): MatchData? {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}