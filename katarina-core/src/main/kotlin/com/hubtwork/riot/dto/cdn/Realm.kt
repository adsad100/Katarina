package com.hubtwork.riot.dto.cdn

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.jetbrains.annotations.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class Realm(
    @NotNull
    var n: Map<String, String>,

    var v: String,      // DD ver
    var l: String,      // Language
    var cdn: String,    // DD base url
    var dd: String,     //
    var lg: String,
    var css: String,
    var profileiconmax: Int
)
