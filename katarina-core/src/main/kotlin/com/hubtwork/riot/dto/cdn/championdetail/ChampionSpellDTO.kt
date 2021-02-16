package com.hubtwork.riot.dto.cdn.championdetail

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hubtwork.riot.dto.cdn.Image

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChampionSpellDTO(
    var id: String,                 // 스킬 key id
    var name: String,               // 스킬 명
    var description: String,        // 스킬 기본 설명
    var tooltip: String,            // 스킬 툴팁

    var image: Image,               // request :: http://ddragon.leagueoflegends.com/cdn/{ dd_ver }/img/spell/{ image.full }

    /**
     *  leveltip    [ value mapping rule ]
     *
     *  - label { l1, l2, ... }
     *      ex) 소모값
     *      ex) 피해량
     *  - effect { e1, e2, ... }
     *      ex) cost > [ cost ]
     *      ex) eN > [ effect(N) ]
     *      ex) aN, fN > [ in vars ]
     */
    var leveltip: LevelTipDTO?,

    var maxrank: Int?,                   // 스킬 최대 레벨
    var cooldown: ArrayList<Double>?,    // 스킬 레벨별 재사용 대기시간
    var cooldownBurn: String?,           // 재사용 대기시간 요약
    var cost: ArrayList<Int>?,

    var costBurn: String?,
    var effect: ArrayList<ArrayList<Double>>?,
    var effectBurn: ArrayList<String>?,

    var vars: ArrayList<SpellVarsDTO>?,
    var costType: String?,           // 스킬 코스트
    var maxammo: String?,
    var range: ArrayList<Integer>?,  // 스킬 사정거리
    var rangeBurn: String?,
    var resource: String?
)