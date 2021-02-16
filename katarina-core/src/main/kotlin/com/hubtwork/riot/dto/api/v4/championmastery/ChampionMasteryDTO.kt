package com.hubtwork.riot.dto.api.v4.championmastery

/**
 *      Get champion mastery Info by encryptedSummonerId
 *
 *      > This object contains single Champion Mastery information for player and champion combination.
 *
 *      response about "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}
 *
 */
data class ChampionMasteryDTO(
    var chestGranted: Boolean,      // 현재 시즌 해당 챔피언 상자 획득 여부
    var championLevel: Int,         // 플레이어의 챔피언 숙련도 레벨
    var championPoints: Int,        // 플레이어의 챔피언 숙련도 점수

    var championId: Long,           // 챔피언 매핑 ID
    var championPointsUntilNextLevel: Long, // 챔피언 다음 레벨 필요포인트
    var lastPlayTime: Long,         // 챔피언 마지막 플레이 시간 ( Unix ms time format :: 13자리 Long )
    var tokensEarned: Int,          // 해당 챔피언 토큰 획득 개수
    var championPointsSinceLastLevel: Long, // 챔피언 숙련도 레벨업 후 얻은 포인트
    var summonerId: String,         // EncryptedSummonerId
)
