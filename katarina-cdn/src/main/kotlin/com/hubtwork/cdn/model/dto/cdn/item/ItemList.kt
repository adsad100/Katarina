package com.hubtwork.cdn.model.dto.cdn.item

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hubtwork.cdn.model.dto.cdn.item.ItemTree

@JsonIgnoreProperties(ignoreUnknown = true)
data class ItemList(
    var version: String,
    var type: String,

    var data: Map<Int, Item>,        // Map < Item-Key, Item > 맵

    var groups: ArrayList<Group>,
    var tree: ArrayList<ItemTree>
){
    fun getItemEntityList() : ArrayList<com.hubtwork.cdn.api.domain.Item>{
        var itemEntityList:ArrayList<com.hubtwork.cdn.api.domain.Item> = ArrayList()
        for(d in data){
            itemEntityList.add(com.hubtwork.cdn.api.domain.Item(d.key, d.value.name, d.value.description, d.value.plaintext,d.value.gold.base, d.value.gold.total, d.value.image.full))
        }
        return itemEntityList
    }
}