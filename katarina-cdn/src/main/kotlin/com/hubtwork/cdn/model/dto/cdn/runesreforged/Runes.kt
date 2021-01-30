package com.hubtwork.cdn.model.dto.cdn.runesreforged

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.hubtwork.cdn.api.domain.Rune

@JsonIgnoreProperties(ignoreUnknown = true)
data class Runes(
    var id: Int,        //  Rune ID
    var key: String,    //  Rune KEY
    var icon: String,   //  Rune image URI
    var name: String,   //  Rune NAME
    var slots: ArrayList<Slot>
){
    fun getRuneList(): ArrayList<Rune>{
        var runeList: ArrayList<Rune> = ArrayList()
        fun Int.checkFirstIndex() = this == 0
        slots.forEachIndexed { index, slot->
            slot.runes?.forEach{
                    runeList.add(Rune(it.id,this.key,this.name,it.key,it.name, index.checkFirstIndex(), it.longDesc,it.icon))
            }
        }
        return runeList
    }
}
