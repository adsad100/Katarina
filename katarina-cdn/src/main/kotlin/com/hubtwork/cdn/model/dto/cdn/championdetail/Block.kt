package com.hubtwork.cdn.model.dto.cdn.championdetail

data class Block(
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

    var items: ArrayList<BlockItem>,

    var recMath: Boolean,
    var recSteps: Boolean,


    )