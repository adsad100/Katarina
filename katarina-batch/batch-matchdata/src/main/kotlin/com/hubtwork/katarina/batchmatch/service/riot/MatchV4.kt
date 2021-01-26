package com.hubtwork.katarina.batchmatch.service.riot

import com.hubtwork.katarina.batchmatch.domain.riot.v4.match.MatchDTO
import com.hubtwork.katarina.batchmatch.domain.riot.v4.match.MatchlistDTO
import reactor.core.publisher.Mono

interface MatchV4 {

    fun getMatchById(matchId: Long): Mono<MatchDTO>?

    fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>?

}