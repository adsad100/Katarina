package com.hubtwork.katarinaapi.dto.riotapi.katarina.match

data class Match(

    val gameType: String,
    val gameMap: Int,
    val gameStart: Long,
    val gameEnd: Long,

    val players: ArrayList<Pick>
)
