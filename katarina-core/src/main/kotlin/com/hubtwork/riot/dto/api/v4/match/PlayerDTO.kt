package com.hubtwork.riot.dto.api.v4.match

data class PlayerDTO(
    var currentPlatformId: String,      //  플랫폼 ID
    var matchHistoryUri: String,        // ?
    var platformId: String,             //  플랫폼 ID

    var summonerName: String,           //  소환사명
    var currentAccountId: String,       //  현재 계정 ID
    var profileIcon: Int,               //  소환사 아이콘
    val summonerId: String,             //  EncryptedSummonerId
    val accountId: String               //  EncryptedAccountId
)