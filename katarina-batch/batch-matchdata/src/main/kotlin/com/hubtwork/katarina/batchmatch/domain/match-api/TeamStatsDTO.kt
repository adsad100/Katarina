package com.hubtwork.katarina.batchmatch.domain.`match-api`

data class TeamStatsDTO(
    var teamID : Int, // 100 : Blue, 200 : Red
    var win: String,  // Win : 승리, Fail : 패배

    var firstBlood: Boolean,// 퍼블 여부
    var firstTower: Boolean, // 포블 여부
    var firstInhibitor: Boolean,    // 첫 억제기 파괴 여부
    var firstBaron: Boolean, // 첫 바론 여부
    var firstDragon: Boolean,   // 첫 용 여부
    var firstRiftHerald: Boolean, // 첫 전령 여부

    var towerKills: Int,        // 타워 철거 갯수
    var inhibitorKills: Int,    // 억제기 파괴 갯수
    var baronKills: Int,        // 바론 처치 횟수
    var dragonKills: Int,       // 용 처치 회수
    var riftHeraldKills: Int,   // 전령 처치 횟수
    var Bans: ArrayList<TeamBansDTO>?    // 밴 카드        :: 일반겜의 경우 밴 정보 없을 수 있음.

    // ----- Deprecated
    // var dominionVictoryScore: Int, // < 도미니언 게임 관련 > 도미니언 결과 점수
    // var vilemawKills: Int,  // < 도미니언 게임 관련 > Vilemaw 처치 횟수
)