package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.MatchData
import com.hubtwork.katarina.batchmatch.api.repository.UserWithMatchRepository

class UserWithMatchService(val repository: UserWithMatchRepository) {
    fun create(accountId: String, matchId: Long) {
        TODO("Not yet implemented")
    }
}