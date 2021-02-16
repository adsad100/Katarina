package com.hubtwork.riot.dto.cdn.championdetail

data class BlockDTO(
    var type: String,

    /*
                Deprecated

        - 소환사 레벨 제약
    var minSummonerLevel: Int,
    var maxSummonerLevel: Int,

        - 소환사 스펠 제약
    var showIfSummonerSpell: String,
    var hideIfSummonerSpell: String,

     */

    var itemDTOS: ArrayList<BlockItemDTO>,

    var recMath: Boolean,
    var recSteps: Boolean,


    )