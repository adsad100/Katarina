package com.hubtwork.riot.api.interfaces

import com.hubtwork.riot.dto.api.v4.platformdata.PlatformDataDTO
import reactor.core.publisher.Mono

interface LolStatusV4 {

    fun getPlatformStatus(): Mono<PlatformDataDTO>

}