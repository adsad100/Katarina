package com.hubtwork.cdn.model.dto.cdn.championdetail

/**
 *      DTO for Detail Data About Target Champion
 *          - request: http://ddragon.leagueoflegends.com/cdn/{ dd_ver }/data/{ language }/champion/{ champion key(eng name) }.json
 *
 *      1. type :: dd data info type ( champion )
 *      2. format :: standAloneComplex ( ? )
 *      3. version :: current Champion Data's DD Version
 *
 *      data :: Detail Data About Target Champion ( Include recommend Set also )
 *
 */
data class ChampionDetail(
    var type: String,
    var format: String,
    var version: String,

    var data: Map<String, ChampionDetailInfo>
){
    fun getChampionListEntity(): ArrayList<com.hubtwork.cdn.api.domain.Champion>{
        var championListEntity: ArrayList<com.hubtwork.cdn.api.domain.Champion> = ArrayList()
        for(d in data){
            championListEntity.add(com.hubtwork.cdn.api.domain.Champion(d.value.key.toInt(), d.value.name, d.value.image.full, d.value.title))
        }
        return championListEntity
    }
}
