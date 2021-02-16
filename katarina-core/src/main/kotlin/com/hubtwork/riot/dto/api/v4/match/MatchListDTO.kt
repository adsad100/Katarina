package com.hubtwork.riot.dto.api.v4.match

/**
 *          get match Info of particular Summoner
 *
 * response about "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/{encryptedAccountId}"
 *
 * get filtering Parameters ( Optional )
 *  1. champion (int)                   ( champion targeting )
 *  2. queue    (int)                   ( queue ID targeting )
 *  3. season   (int)                   ( season targeting :: deprecated )
 *  4. beginTime & endTime ( long )     ( filtering by time )
 *  5. beginIndex & endIndex ( int )    ( filtering by index Range - maximum range : 100 )
 *
 *  6. platformId ( String )            ( platform Info :: KR, NA , ... )
 *
 *
 *  return ArrayList<MatchInfo>
 */

data class MatchListDTO(

    var matches: ArrayList<MatchReferenceDTO> = arrayListOf(),  // 매치 정보 List

    var totalGames: Int,            // 총 게임 수
    var startIndex: Int,            // 시작 인덱스
    var endIndex: Int,              // 종료 인덱스

)
