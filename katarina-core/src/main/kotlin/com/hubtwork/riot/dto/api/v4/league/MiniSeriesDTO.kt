package com.hubtwork.riot.dto.api.v4.league

/**
 *              get summoner LeagueEntry DATA by Encrypted Summoner ID
 *
 *              > 승급전 정보
 *
 *  response about "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{Encrypted Summoner Id}"
 */

data class MiniSeriesDTO(
    var progress: String,   // 현재 승급전 진행상황 joined String ( Y: 승리, L : 패배,  N: 남은 게임 )
    var target: Int,        // 다음 승급전 라운드

    var wins: Int,          // 승리 횟수
    var loses: Int          // 패배 횟수
)
