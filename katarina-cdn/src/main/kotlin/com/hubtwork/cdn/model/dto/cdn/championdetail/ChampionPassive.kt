package com.hubtwork.cdn.model.dto.cdn.championdetail

import com.hubtwork.cdn.model.dto.Image

/**
 *      DTO for Champion's Passive Skill
 *
 */

data class ChampionPassive (
    var name: String,
    var description: String,
    var image: Image
)
