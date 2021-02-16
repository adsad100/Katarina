package com.hubtwork.riot.api.interfaces

import com.hubtwork.riot.dto.api.v4.league.LeagueEntryDTO
import com.hubtwork.riot.dto.api.v4.league.LeagueListDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface LeagueV4 {

    fun getChallengerLeague(queue: String): Mono<LeagueListDTO>
    fun getGrandMasterLeague(queue: String): Mono<LeagueListDTO>
    fun getMasterLeague(queue: String): Mono<LeagueListDTO>
    fun getAllLeague(queue: String, tier: String, division: String): Flux<LeagueEntryDTO>

    fun getLeagueByLeagueId(leagueId: String): Mono<LeagueListDTO>
    fun getLeagueBySummonerId(encryptedSummonerId: String): Flux<LeagueEntryDTO>

}