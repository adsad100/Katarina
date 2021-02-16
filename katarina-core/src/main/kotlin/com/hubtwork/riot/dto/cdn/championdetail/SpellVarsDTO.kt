package com.hubtwork.riot.dto.cdn.championdetail

/**
 *          TODO - Analyze this DTO
 *
 */
data class SpellVarsDTO(
    /**
     *      ISSUE
     *
     *      only in summonerspell "SummonerBoost", SpellVar's coeff variable is single Double value.
     *      so mapping to any and pipelining when use this data.
     *
     */
    var coeff: Any?,

    var ranksWith: String?,
    var dyn: String?,
    var link: String?,
    var key: String?
)
