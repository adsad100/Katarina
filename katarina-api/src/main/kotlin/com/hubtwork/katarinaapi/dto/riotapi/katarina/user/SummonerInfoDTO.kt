package com.hubtwork.katarinaapi.dto.riotapi.katarina.user

data class SummonerInfoDTO(
    var revisionDate: Long,         // 소환사 정보 갱신 시간 ( 소환사명, 프로필 아이콘, 레벨 변동 기준 )

    var encryptedAccountId: String,

    var name: String,
    var summonerLevel: Long,
    var profileIconId: Int
)