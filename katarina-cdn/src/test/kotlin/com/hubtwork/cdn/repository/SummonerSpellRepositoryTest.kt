package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.SummonerSpell
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.SummonerSpellRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.api.service.SummonerSpellService
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
import javax.transaction.Transactional

class SummonerSpellRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var summonerSpellRepository: SummonerSpellRepository

    @Autowired
    lateinit var versionRepository: VersionRepository

    @Test
    fun create(){
        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val cdnService = CdnService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val summonerSpellService = SummonerSpellService(summonerSpellRepository, restTemplate)
        var summonerVersion = versionRepository.getOne(1).summoner

        val flag = summonerSpellService.summonerVersionCheck(summonerVersion)

        if(!flag) {

            summonerSpellRepository.deleteAll()

            val spellList = realm.n["summoner"]?.let{
                cdnService.getSummonerSpells(platform, Locale.KO_KR, it)
            }
            spellList?.body!!.getSummonerSpellEntityList().forEach{
                summonerSpellService.create(it)
            }

            val summonerSpellIdList: List<Int> = summonerSpellRepository.findAllSummonerSpellId()
            summonerSpellIdList.forEach{
                //todo - change eachId : complete
                val eachId: Optional<SummonerSpell> = summonerSpellRepository.findById(it)
                eachId.ifPresent {
                    val currentVersion = realm.n["summoner"].toString()
                    val newImage = summonerSpellService.addUrl(currentVersion, eachId.get().image)
                    it.image = newImage
                    summonerSpellRepository.save(it)
                }
            }

            summonerVersion = realm.n["summoner"].toString()

            val version: Optional<Version> = versionRepository.findById(1)

            version.ifPresent {
                it.summoner = summonerVersion
                it.updatedAt = LocalDateTime.now()
                versionRepository.save(it)
            }
        }
    }



    @Test
    fun read(){
        val spell: Optional<SummonerSpell> = summonerSpellRepository.findById(1)

        spell.ifPresent {
            println("소환사주문 아이디 : " + it.id)
            println("소환사주문 영어 이름 : " + it.englishName)
            println("소환사주문 한국 이름 : " + it.koreanName)
            println("소환사주문 description : " + it.description)

            Assertions.assertNotNull(spell)
        }
    }

    @Test
    @Transactional
    fun delete(){
        val spell: Optional<SummonerSpell> = summonerSpellRepository.findById(1)

        Assertions.assertTrue(spell.isPresent)

        spell.ifPresent {
            summonerSpellRepository.delete(it)
        }

        val deleteSpell: Optional<SummonerSpell> = summonerSpellRepository.findById(1)

        Assertions.assertFalse(deleteSpell.isPresent)
    }
}