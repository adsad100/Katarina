package com.hubtwork.riot.dto.cdn.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemListDTO(
    var version: String,
    var type: String,

    var data: Map<Int, ItemDTO>,        // Map < Item-Key, Item > 맵

    var groupDTOS: ArrayList<GroupDTO>,
    var treeDTO: ArrayList<ItemTreeDTO>
){

}