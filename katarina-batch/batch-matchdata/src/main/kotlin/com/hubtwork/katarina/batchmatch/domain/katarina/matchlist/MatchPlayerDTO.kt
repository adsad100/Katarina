package com.hubtwork.katarinaapi.dto.katarina.matchlist

import com.hubtwork.katarinaapi.dto.katarina.match.Items
import com.hubtwork.katarinaapi.dto.katarina.match.KDA
import com.hubtwork.katarinaapi.dto.katarina.match.Perk
import com.hubtwork.katarinaapi.dto.katarina.match.Statistics

data class MatchPlayerDTO(
    // 팀 정보
    var team: Int,
    var win: Boolean,

    // 라인 정보
    var lane: String,

    // 소환사 정보
    var summonerName: String,
    var accountId: String,

    // 챔피언 정보
    var champion: Int,
    // 스펠 정보
    var spell1: Int,
    var spell2: Int,

    // 룬 정보
    var rune: Perk,

    // KDA 정보
    var kda: KDA,

    // 아이템 정보
    var item: Items,

    // 통계 정보
    var statistics: Statistics
)

