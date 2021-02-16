package com.hubtwork.katarina.data.processed

data class ChampionRecordDTO(
    // Champion Id
    var id: Int,
    // WinningRate
    var win: Int,
    var lose: Int,
    var winRate: Double,
)
