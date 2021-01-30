package com.hubtwork.cdn.api.domain

import com.hubtwork.cdn.model.dto.Image
import javax.persistence.*


@Entity
@Table(name = "summoner_spell")
class SummonerSpell(
    name_EN: String,
    name_KO: String,
    description: String,
    image: String
) {
    @Id
    @Column(name = "spell_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    @Column(name= "name_english")
    val englishName = name_EN

    @Column(name= "name_korean")
    val koreanName = name_KO

    @Column(name= "description", columnDefinition = "TEXT")
    val description = description

    @Column(name= "image")
    var image = image

}