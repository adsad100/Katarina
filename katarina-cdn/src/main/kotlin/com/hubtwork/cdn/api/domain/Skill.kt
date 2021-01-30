package com.hubtwork.cdn.api.domain

import java.io.Serializable
import javax.persistence.*

class SkillId(
        var championId: Int = 0,
        var skillType: String = ""
): Serializable{
    fun SkillId(championId: Int, skillType: String) {
        this.championId = championId
        this.skillType = skillType
    }
}

@Entity
@Table(name="champion_skill")
@IdClass(SkillId::class)
class Skill (
        champion: Champion,
        skillType: String,
        name: String,
        description: String,
        image: String
        ){
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="champion_id")
    var championId: Champion = champion

    @Id
    @Column(name = "skill_type")
    var skillType = skillType

    @Column(name="name")
    var name = name

    @Column(name= "description", columnDefinition = "TEXT")
    var description = description

    @Column(name = "image")
    var image = image
}