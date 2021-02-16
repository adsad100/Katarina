package com.hubtwork.riot.dto.cdn.runesreforged

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RunesDTO(
    var id: Int,        //  Rune ID
    var key: String,    //  Rune KEY
    var icon: String,   //  Rune image URI
    var name: String,   //  Rune NAME
    var slotDTOS: ArrayList<SlotDTO>
){

}
