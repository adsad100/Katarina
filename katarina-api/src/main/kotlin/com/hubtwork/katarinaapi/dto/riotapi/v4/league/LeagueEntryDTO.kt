package com.hubtwork.katarinaapi.dto.riotapi.v4.league

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hubtwork.katarinaapi.dto.katarina.user.RecordDTO
import kotlin.math.roundToInt

@JsonIgnoreProperties(ignoreUnknown = true)
data class LeagueEntryDTO(
    var leagueId: String,

    var summonerId: String, // Encrypted Summoner ID
    var summonerName: String,
    var queueType: String,  // 랭크 종류
    var tier: String,   // 현재 티어
    var rank: String,   // 랭크 ( 티어 단계 )
    var leaguePoints: Int, // 점수
    var wins: Int,  // 승리 수
    var losses: Int,    // 패배 수
    // 아래 사항은 개발자 포럼 참고해볼 것
    var veteran: Boolean,           // 장기 계정 여부 ( 현재 리그에서 100판 이상 진행 )
    var inactive: Boolean,          // 휴면 계정 여부 ( 한 달 이상 랭크 기록 X )
    var freshBlood: Boolean,        // 신규 계정 여부
    var hotStreak: Boolean          // 연승 여부
)
{
    fun getRankRecord(): RecordDTO =
        RecordDTO(queueType, "$tier $rank $leaguePoints",
            wins, losses, ((1000 * (wins.toDouble() / (wins + losses))).roundToInt().toDouble())/10)
    fun getTierString() = "$tier $rank $leaguePoints"
}