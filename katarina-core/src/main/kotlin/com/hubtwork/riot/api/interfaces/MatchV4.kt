package com.hubtwork.riot.api.interfaces

import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import com.hubtwork.riot.dto.api.v4.match.MatchListDTO
import reactor.core.publisher.Mono

interface MatchV4 {

    fun getMatchById(matchId: Long): Mono<MatchDTO>

    fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchListDTO>

}