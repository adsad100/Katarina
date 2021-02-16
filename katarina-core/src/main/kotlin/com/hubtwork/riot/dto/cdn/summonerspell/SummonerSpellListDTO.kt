package com.hubtwork.riot.dto.cdn.summonerspell

data class SummonerSpellListDTO(
    var type: String,
    var version: String,

    var data: Map<String, SummonerSpellDTO>,
){

}
