package com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 *              get summoner Basic DATA by Summoner Name
 *
 *  response about "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/{summoner_name}"
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class SummonerDTO(
    var id: String,                 // Encrypted Summoner ID
    var accountId: String,          // Encrypted account ID
    var puuid: String,              // Encrypted PUUID
    var revisionDate: Long,         // 소환사 정보 갱신 시간 ( 소환사명, 프로필 아이콘, 레벨 변동 기준 )

    var name: String,
    var summonerLevel: Long,
    var profileIconId: Int
)