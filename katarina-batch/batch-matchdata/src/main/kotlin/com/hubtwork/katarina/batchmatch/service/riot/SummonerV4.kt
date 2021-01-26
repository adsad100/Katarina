package com.hubtwork.katarina.batchmatch.service.riot

import com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner.SummonerDTO
import reactor.core.publisher.Mono

interface SummonerV4 {

    fun getSummonerByName(summonerName: String): Mono<SummonerDTO>?
    fun getSummonerByPUUID(encryptedPUUID: String): Mono<SummonerDTO>?
    fun getSummonerByAccountId(encryptedAccountId: String): Mono<SummonerDTO>?
    fun getSummonerBySummonerId(encryptedSummonerId: String): Mono<SummonerDTO>?

}