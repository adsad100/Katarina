package com.hubtwork.riot.dto.cdn.championdetail

import com.hubtwork.riot.dto.cdn.Image

/**
 *      DTO for Champion's Passive Skill
 *
 */

data class ChampionPassiveDTO (
    var name: String,
    var description: String,
    var image: Image
)
