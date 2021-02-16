package com.hubtwork.riot.dto.cdn.champion

import com.hubtwork.riot.dto.cdn.Image

data class ChampionDTO(
    var version: String,
    // Key values
    var id: String,         // ChampionList's Key
    var key: Int,           // Primary Key

    var name: String,       // Translated Champion Name based on platform ( 플랫폼 기반 번역된 이름 )
    var title: String,      // Champion's Alias ( 챔피언 별명 )
    var blurb: String,      // Description about champion's story

    var infoDTO: ChampionInfoDTO,
    var image: Image,

    var tags: ArrayList<String>,
    var partype: String,            // MP Type ( 기력, 마나, 피의샘 등등 )

    var stats: ChampionStatDTO
)