package com.hubtwork.riot.dto.cdn.summonerspell

import com.hubtwork.riot.dto.cdn.Image
import com.hubtwork.riot.dto.cdn.championdetail.SpellVarsDTO

data class SummonerSpellDTO(

    var id: String,
    var name: String,
    var description: String,
    var tooltip: String,
    var maxrank: Int,
    var cooldown: ArrayList<Int>,
    var cooldownBurn: String,
    var cost: ArrayList<Int>,
    var costBurn: String,
    var datavalues: DataValueDTO,
    var effect: ArrayList<ArrayList<Double>>,
    var effectBurn: ArrayList<String>,
    var vars: ArrayList<SpellVarsDTO>,
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