package com.hubtwork.cdn.model.dto.cdn.championdetail

data class ChampionSkin(

    var id: String,         // 스킨 id
    var num: Int,           // 스킨 스플래시 순번 ... Img Resource : http://ddragon.leagueoflegends.com/cdn/img/champion/splash/{ championID }_{ num }.jpg
    var name: String,       // 스킨 이름
    var chromas: Boolean    // 크로마 스킨 제공 여부
)
