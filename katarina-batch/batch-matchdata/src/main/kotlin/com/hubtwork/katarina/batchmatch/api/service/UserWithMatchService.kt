package com.hubtwork.katarina.batchmatch.api.service

import com.hubtwork.katarina.batchmatch.api.domain.UserWithMatch
import com.hubtwork.katarina.batchmatch.api.repository.UserWithMatchRepository
import org.springframework.stereotype.Service

@Service
class UserWithMatchService(val repository: UserWithMatchRepository) {

    /**
     * matchIds :: Pair< accountId<Long>, matchEndTime<Long> >
     */
    fun create(accountId: String, matchId: Long, queueType: Int, matchEndTime: Long) {
        repository.save(UserWithMatch(accountId, matchId, queueType, matchEndTime))
    }

    fun getMatchListOfCurrentUser(accountId: String): List<UserWithMatch> =
        repository.getMatchesByAccountId(accountId)

    fun getLastMatchOfCurrentUser(accountId: String): UserWithMatch? =
        repository.getLastMatchByAccountId(accountId)

    fun getAllDataCount(): Long =
        repository.findAll().size.toLong()

}