package com.hubtwork.cdn.model.dto.cdn.champion

import com.hubtwork.cdn.model.dto.Image

data class Champion(
    var version: String,
    // Key values
    var id: String,         // ChampionList's Key
    var key: Int,           // Primary Key

    var name: String,       // Translated Champion Name based on platform ( 플랫폼 기반 번역된 이름 )
    var title: String,      // Champion's Alias ( 챔피언 별명 )
    var blurb: String,      // Description about champion's story

    var info: ChampionInfo,
    var image: Image,

    var tags: ArrayList<String>,
    var partype: String,            // MP Type ( 기력, 마나, 피의샘 등등 )

    var stats: ChampionStat
)