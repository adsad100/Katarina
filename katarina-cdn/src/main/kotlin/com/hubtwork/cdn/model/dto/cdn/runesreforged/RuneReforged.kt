package com.hubtwork.cdn.model.dto.cdn.runesreforged

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RuneReforged (
    var id: Int,            //  Rune ID
    var key: String,        //  Rune Key ( eng name )
    var icon: String,       //  룬 메인 아이콘
    var name: String,       //  룬 이름
    //  룬 요약 정보 ( Katarina - Deprecated :: 안 쓸 거임 )
    // var shortDesc: String,
    var longDesc: String    //  룬 정보
)