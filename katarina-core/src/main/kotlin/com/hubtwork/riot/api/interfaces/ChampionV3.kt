package com.hubtwork.riot.api.interfaces

import com.hubtwork.riot.dto.api.v3.champion.ChampionInfoDTO
import reactor.core.publisher.Mono

interface ChampionV3 {

    fun getChampionRotations(): Mono<ChampionInfoDTO>
}