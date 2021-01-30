package com.hubtwork.cdn.model.dto.cdn.summonerspell

import com.hubtwork.cdn.model.dto.cdn.summonerspell.DataValue
import com.hubtwork.cdn.model.dto.Image
import com.hubtwork.cdn.model.dto.cdn.championdetail.SpellVars

data class SummonerSpell(

    var id: String,
    var name: String,
    var description: String,
    var tooltip: String,
    var maxrank: Int,
    var cooldown: ArrayList<Int>,
    var cooldownBurn: String,
    var cost: ArrayList<Int>,
    var costBurn: String,
    var datavalues: DataValue,
    var effect: ArrayList<ArrayList<Double>>,
    var effectBurn: ArrayList<String>,
    var vars: ArrayList<SpellVars>,
    var key: String,
    var summonerLevel: Int,
    var modes: ArrayList<String>,
    var costType: String,
    var maxammo: String,
    var range: ArrayList<Int>,
    var rangeBurn: String,

    var image: Image,

    var resource: String

)