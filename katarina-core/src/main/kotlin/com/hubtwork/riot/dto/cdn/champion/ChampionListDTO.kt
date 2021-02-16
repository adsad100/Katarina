package com.hubtwork.riot.dto.cdn.champion

import com.hubtwork.riot.dto.cdn.championdetail.ChampionDetailInfoDTO

data class ChampionListDTO(
    var type: String,
    var format: String,
    var version: String,

    var data: Map<String, ChampionDetailInfoDTO>,
){

}
