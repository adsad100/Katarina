package com.hubtwork.cdn.model.dto.cdn.summonerspell

import com.hubtwork.cdn.model.dto.cdn.summonerspell.SummonerSpell

data class SummonerSpellList(
    var type: String,
    var version: String,

    var data: Map<String, SummonerSpell>,
){
    fun getSummonerSpellEntityList(): ArrayList<com.hubtwork.cdn.api.domain.SummonerSpell>{
        var summonerSpellList: ArrayList<com.hubtwork.cdn.api.domain.SummonerSpell> = ArrayList()
        for(d in data){
            summonerSpellList.add(com.hubtwork.cdn.api.domain.SummonerSpell(d.value.id,d.value.name,d.value.description,d.value.image.full))
        }
        return summonerSpellList
    }
}
