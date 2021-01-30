package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.Champion
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.ChampionRepository
import com.hubtwork.cdn.api.repository.SkillRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.api.service.ChampionService
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

class ChampionRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var championRepository: ChampionRepository

    @Autowired
    lateinit var versionRepository: VersionRepository

    @Autowired
    lateinit var skillRepository: SkillRepository

    @Test
    fun create(){
        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val cdnService = CdnService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val championService = ChampionService(championRepository, restTemplate)
        var championVersion = versionRepository.getOne(1).champion

        val flag = championService.championVersionCheck(championVersion)

        if(!flag) {

            skillRepository.deleteAll()
            championRepository.deleteAll()

            val championInfoList = realm.n["champion"]?.let{
                cdnService.getAllChampions(platform, Locale.KO_KR, it)
            }
            val (championList, championSkillList) = championInfoList?.body!!.getChampionDetailList()

            championList?.forEach{
                championService.create(it)
            }

            val championIdList: List<Int> = championRepository.findAllChampionId()
            championIdList.forEach{
                val eachId: Optional<Champion> = championRepository.findById(it)
                eachId.ifPresent {
                    //todo - code optimization
                    val currentVersion = realm.n["champion"].toString()
                    val newImage = championService.addUrl(currentVersion,eachId.get().image)
                    it.image = newImage
                    championRepository.save(it)
                }
            }

            //todo - code optimization
            championVersion = realm.n["champion"].toString()

            val version: Optional<Version> = versionRepository.findById(1)

            version.ifPresent {
                it.champion = championVersion
                it.updatedAt = LocalDateTime.now()
                versionRepository.save(it)
            }
        }

    }

    @Test
    fun read(){
        val champion: Optional<Champion> = championRepository.findById(8)

        champion.ifPresent {
            println("챔피언 아이디 : " + it.id)
            println("챔피언 이름 : " + it.name)
            println("챔피언 타이틀 : " + it.title)
            println("챔피언 이미지 : " + it.image)

            Assertions.assertNotNull(champion)
        }
    }

    @Test
    @Transactional
    fun delete(){
        val champion: Optional<Champion> = championRepository.findById(8)

        Assertions.assertTrue(champion.isPresent)

        champion.ifPresent {
            championRepository.delete(it)
        }

        val deleteChampion: Optional<Champion> = championRepository.findById(8)

        Assertions.assertFalse(deleteChampion.isPresent)
    }
}