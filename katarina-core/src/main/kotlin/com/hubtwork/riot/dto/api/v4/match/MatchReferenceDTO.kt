package com.hubtwork.riot.dto.api.v4.match

/**
 *          get particular match Info of particular Summoner
 *
 *
 */

data class MatchReferenceDTO(
    var platformId: String,
    var gameId: Long,       // matchId
    var champion: Int,      // championId
    var queue: Int,         // queueId
    var season: Int,        // 시즌Id
    var timestamp: Long,    // 타임스탬프
    var role: String,       // ?            ( DUO, SOLO, NONE ... etc )
    var lane: String        // 큐 돌릴 때 선택한 라인
)

