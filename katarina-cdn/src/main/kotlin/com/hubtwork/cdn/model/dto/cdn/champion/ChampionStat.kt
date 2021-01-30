package com.hubtwork.cdn.model.dto.cdn.champion

data class ChampionStat (
    var hp: Int,
    var hpperlevel: Int,
    var mp: Int,
    var mpperlevel: Int,

    var movespeed: Int,
    var armor: Int,                     // 방어
    var armorperlevel: Double,
    var spellblock: Double,             // 마저
    var spellblockperlevel: Double,

    var attackrange: Int,
    var hpregen: Int,
    var hpregenperlevel: Int,

    var crit: Int,
    var critperlevel: Int,
    var attackdamage: Int,
    var attackdamageperlevel: Int,
    var attacksppedperlevel: Double,
    var attackspeed: Double
)