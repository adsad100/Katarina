package com.hubtwork.katarinaapi.service.katarina.`interface`

import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchlistDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.summoners.SummonerDTO
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono

interface SummonerAPI {

    fun getAccountId(summonerName: String): Mono<SummonerDTO>?

    fun getSummonerData(summonerName: String): ResponseEntity<Any>

    fun getMatch(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>

    fun getMatchRecords(encryptedAccountId: String, ): ResponseEntity<Any>

    fun getChampionRecords(encryptedAccountId: String, ): ResponseEntity<Any>

    fun getFriendRecords(encryptedAccountId: String, ): ResponseEntity<Any>

}