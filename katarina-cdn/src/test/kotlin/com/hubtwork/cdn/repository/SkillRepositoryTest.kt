package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.Skill
import com.hubtwork.cdn.api.domain.SkillId
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.SkillRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.api.service.SkillService
import com.hubtwork.cdn.constant.Locale
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.cdn.CdnService
import com.hubtwork.cdn.service.realms.RealmService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.*

class SkillRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var skillRepository: SkillRepository

    @Autowired
    lateinit var versionRepository: VersionRepository


    @Test
    fun create() {
        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val cdnService = CdnService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val skillService = SkillService(skillRepository, restTemplate)
        var skillVersion = versionRepository.getOne(1).champion

        val flag = skillService.skillVersionCheck(skillVersion)

        if (!flag) {

            skillRepository.deleteAll()

            val championInfoList = realm.n["champion"]?.let {
                cdnService.getAllChampions(platform, Locale.KO_KR, it)
            }
            val (championList, championSkillList) = championInfoList?.body!!.getChampionDetailList()

            championSkillList?.forEach {
                skillService.create(it)

            }
            //todo - implementing image URL : complete
            val unique = skillRepository.findUniqueId()
            unique.forEach {
                val each = skillRepository.selectAllById(it)
                each.forEach { e->
                    val currentVersion = realm.n["champion"].toString()
                    if(e.skillType == "Passive"){
                        e.image = skillService.addPassiveUrl(currentVersion, e.image)
                    }else{
                        e.image = skillService.addSkillUrl(currentVersion, e.image)
                    }
                    skillRepository.save(e)
                }
            }

            skillVersion = realm.n["champion"].toString()

            val version: Optional<Version> = versionRepository.findById(1)

            version.ifPresent {
                it.champion = skillVersion
                it.updatedAt = LocalDateTime.now()
                versionRepository.save(it)
            }
        }
    }

    @Test
    fun read(){
        val skill: Optional<Skill> = skillRepository.findById(SkillId(875,"SettE"))

        skill.ifPresent {
            println("챔피언 아이디 : " + it.championId)
            println("스킬 타입 : " + it.skillType)
            println("스킬 이름 : " + it.name)
            println("스킬 description : " + it.description)
            println("스킬 이미지 : " + it.image)

            Assertions.assertNotNull(skill)
        }
    }

    @Test
    fun delete(){
        val skill: Optional<Skill> = skillRepository.findById(SkillId(875,"SettE"))

        Assertions.assertTrue(skill.isPresent)

        skill.ifPresent {
            skillRepository.delete(it)
        }

        val deleteSkill: Optional<Skill> = skillRepository.findById(SkillId(875,"SettE"))

        Assertions.assertFalse(deleteSkill.isPresent)
    }

    @Test
    fun test() {
        val distincts = skillRepository.findUniqueId()
        distincts.forEach {
            val skills = skillRepository.selectAllById(it)
            skills.forEach { t->
                println(t.skillType)
            }
        }
    }
}
