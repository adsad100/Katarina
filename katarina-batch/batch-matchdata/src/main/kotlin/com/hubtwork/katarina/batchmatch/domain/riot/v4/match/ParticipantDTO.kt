package com.hubtwork.katarina.batchmatch.domain.riot.v4.match

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hubtwork.katarinaapi.dto.katarina.matchlist.MatchPlayerDTO

@JsonIgnoreProperties(ignoreUnknown = true)
data class ParticipantDTO(
    var participantId: Int,     //  참가자 매핑 ID
    var teamId: Int,            //  팀 ID
    var championId: Int,        //  플레이 챔피언 ID

    var spell1Id: Int,          //  소환사 주문 1
    var spell2Id: Int,          //  소환사 주문 2

    /*
    List of legacy Mastery information. Not included for matches played with Runes Reforged.

    var masteries: ArrayList<MasteryDTO>?,   // 마스터리 정보
    var runes: ArrayList<RuneDTO>?,          // 룬 정보
    */

    var timeline: ParticipantTimelineDTO,   //  해당 플레이어의 타임라인 데이터

    var highestAchievedSeasonTier: String?,  //  해당 플레이어의 전 시즌 탑레이팅 ( 로딩창 표시에 사용 )

    var stats: ParticipantStatsDTO,        //  플레이어 스텟 정보
)
{
    fun getMatchPlayerDTO(): MatchPlayerDTO =
        MatchPlayerDTO(teamId, stats.win, timeline.getLaneData(), "", "", championId, spell1Id, spell2Id,
            stats.getPerks(), stats.getKDA(), stats.getItems(), stats.getStatistics())

}

