package com.hubtwork.cdn.model.dto.cdn.champion

import com.hubtwork.cdn.api.domain.Champion
import com.hubtwork.cdn.api.domain.Skill
import com.hubtwork.cdn.model.dto.cdn.championdetail.ChampionDetailInfo

data class ChampionList(
    var type: String,
    var format: String,
    var version: String,

    var data: Map<String, ChampionDetailInfo>,
){
    /*
     */
    fun getChampionDetailList(): Pair<ArrayList<Champion>, ArrayList<Skill> >{
        var championList: ArrayList<Champion> = ArrayList()
        var championSkillList: ArrayList<Skill> = ArrayList()

        for(championInfo in data.values){
            var champion: Champion = Champion(championInfo.key.toInt(), championInfo.name, championInfo.image.full, championInfo.title)

            championList.add(champion)
            championSkillList.add(Skill(champion, "Passive", championInfo.passive.name, championInfo.passive.description, championInfo.passive.image.full))
            championInfo.spells.forEach{
                championSkillList.add(Skill(champion, it.id, it.name, it.description, it.image.full))
            }

        }

        return Pair(championList, championSkillList)
    }
}
