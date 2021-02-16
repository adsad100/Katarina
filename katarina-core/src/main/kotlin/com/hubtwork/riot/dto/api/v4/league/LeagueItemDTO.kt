package com.hubtwork.riot.dto.api.v4.league

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


/**
 *              get summoner LeagueEntry DATA by Encrypted Summoner ID
 *
 *  response about "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/{Encrypted Summoner Id}"
 */

@JsonIgnoreProperties(ignoreUnknown = true)
data class LeagueItemDTO(
    var summonerId: String, // Encrypted Summoner ID
    var summonerName: String,
    var queueType: String,  // 랭크 종류
    var tier: String,   // 현재 티어
    var rank: String,   // 랭크 ( 티어 단계 )
    var leaguePoints: Int, // 점수
    var wins: Int,  // 승리 수
    var losses: Int,    // 패배 수

    var miniSeries: ArrayList<MiniSeriesDTO> = arrayListOf(),       // 승급전 정보

    // 아래 사항은 개발자 포럼 참고해볼 것
    var veteran: Boolean,           // 장기 계정 여부 ( 현재 리그에서 100판 이상 진행 )
    var inactive: Boolean,          // 휴면 계정 여부 ( 한 달 이상 랭크 기록 X )
    var freshBlood: Boolean,        // 신규 계정 여부
    var hotStreak: Boolean          // 연승 여부
)