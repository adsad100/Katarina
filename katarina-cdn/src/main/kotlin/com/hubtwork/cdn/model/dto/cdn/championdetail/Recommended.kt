package com.hubtwork.cdn.model.dto.cdn.championdetail

import com.hubtwork.cdn.model.dto.cdn.championdetail.Block

data class Recommended(
    var champion: String,
    var title: String,
    var map: String,
    var mode: String,
    var type: String,

    var blocks: ArrayList<Block>

    /*
            Deprecated :: find USEFUL WAY

    var customTag: String,
    var sortrank: Int,
    var extensionPage: Boolean,
    var useObviousCheckmark: Boolean,
    var customPanel: String,

     */


)