package com.hubtwork.riot.dto.cdn.championdetail

data class RecommendedDTO(
    var champion: String,
    var title: String,
    var map: String,
    var mode: String,
    var type: String,

    var blockDTOS: ArrayList<BlockDTO>

    /*
            Deprecated :: find USEFUL WAY

    var customTag: String,
    var sortrank: Int,
    var extensionPage: Boolean,
    var useObviousCheckmark: Boolean,
    var customPanel: String,

     */


)