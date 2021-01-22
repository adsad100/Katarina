package com.hubtwork.katarinaapi.dto.riotapi.katarina.user

data class RecordDTO(

    var type: String,

    // Rank 의 경우만 표시
    var tier: String?,

    var win: Int,
    var lose: Int,
    var winningRate: Double

)
