package com.hubtwork.katarinaapi.dto.riotapi.v4.league

/**
 *          get LeagueEntries by queue
 *
 *              >  queue Type:
 *                  솔랭 - RANKED_SOLO_5x5
 *                  자랭 - RANKED_FLEX_SR
 *                  TFT - RANKED_FLEX_TT
 *
 *      1. 챌린저
 *          https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/{ queue }
 *      2. 그마
 *          https://kr.api.riotgames.com/lol/league/v4/grandmasterleagues/by-queue/{ queue }
 *      3. 마스터
 *          https://kr.api.riotgames.com/lol/league/v4/masterleagues/by-queue/{ queue }
 */

data class LeagueListDTO (

    var leagueId : String,      // League ID
    var tier : String,          // 티어
    var queue : String,         // 큐 타입 ( 솔랭, 자랭, TFT )
    var name : String,          // 리그 명

    var entries : ArrayList<LeagueItemDTO> = arrayListOf()

)