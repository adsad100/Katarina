package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Skill
import com.hubtwork.cdn.api.domain.SkillId
import com.hubtwork.cdn.api.exception.NotFoundException
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.SkillRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class SkillService(val ddSkillRepository: SkillRepository, private val restTemplate: RestTemplate) {
    @Transactional
    fun create(skill: Skill) {
        ddSkillRepository.save(skill)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(skillId: SkillId): Skill {
        // TODO - ITEM Exception
        return ddSkillRepository.findByIdOrNull(skillId) ?: throw NotFoundException.DDVersion.get()
    }

    fun update() {

    }

    fun delete() {

    }

    fun skillVersionCheck(skillVersion: String): Boolean {

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val version = realm.n["champion"].toString()

        return skillVersion == version

    }

    fun addPassiveUrl(version: String, image: String): String{
        val passiveUrl = "http://ddragon.leagueoflegends.com/cdn/$version/img/passive/"
        return passiveUrl + image
    }

    fun addSkillUrl(version: String, image: String): String{
        val skillUrl = "http://ddragon.leagueoflegends.com/cdn/$version/img/spell/"
        return skillUrl + image
    }

    //http://ddragon.leagueoflegends.com/cdn/11.1.1/img/passive/Anivia_P.png

    //http://ddragon.leagueoflegends.com/cdn/11.1.1/img/spell/FlashFrost.png
}