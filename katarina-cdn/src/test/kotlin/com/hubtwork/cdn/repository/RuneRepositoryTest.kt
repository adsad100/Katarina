package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.Rune
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.RuneRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.api.service.RuneService
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

class RuneRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var runeRepository: RuneRepository

    @Autowired
    lateinit var versionRepository: VersionRepository


    @Test
    fun create(){
        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val cdnService = CdnService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val runeService = RuneService(runeRepository, restTemplate)
        var runeVersion = versionRepository.getOne(1).rune

        val flag = runeService.runeVersionCheck(runeVersion)
        //if version change, drop rune table and recreate rune table and get data from CDN
        if(!flag) {

            runeRepository.deleteAll()

            val runeList = realm.n["champion"]?.let{
                cdnService.getRunes(platform, Locale.KO_KR, it)
            }

            runeList?.body!!.forEach{ runeInfo ->
                runeInfo.getRuneList().forEach{
                    runeService.create(it)
                }
            }
            //change imageURL
            val runeIdList: List<Int> = runeRepository.findAllRuneId()
            runeIdList.forEach{
                //todo - change eachId : complete
                val eachId: Optional<Rune> = runeRepository.findById(it)
                eachId.ifPresent {
                    val newImage = runeService.addUrl(eachId.get().image)
                    it.image = newImage
                    runeRepository.save(it)
                }
            }

            //change rune column from version table
            runeVersion = realm.n["champion"].toString()

            val version: Optional<Version> = versionRepository.findById(1)

            version.ifPresent {
                it.rune = runeVersion
                it.updatedAt = LocalDateTime.now()
                versionRepository.save(it)
            }
        }
    }


    @Test
    fun read(){
        val rune: Optional<Rune> = runeRepository.findById(8005)

        rune.ifPresent {
            println("룬 아이디 : " + it.id)
            println("룬 영어 이름 : " + it.nameEnglish)
            println("룬 한국 이름 : " + it.nameKorean)
            println(" description : " + it.longDescription)

            Assertions.assertNotNull(rune)
        }
    }

    @Test
    @Transactional
    fun delete(){
        val rune: Optional<Rune> = runeRepository.findById(8005)

        Assertions.assertTrue(rune.isPresent)

        rune.ifPresent {
            runeRepository.delete(it)
        }

        val deleteRune: Optional<Rune> = runeRepository.findById(8005)

        Assertions.assertFalse(deleteRune.isPresent)


    }

    /*
    @Test
    @Transactional
    fun test() {

        val runeService = RuneService(runeRepository, restTemplate)

        val runeIdList: List<Int> = runeRepository.findAllRuneId()
        runeIdList.forEach {
            val eachId: Optional<Rune> = runeRepository.findById(it)
            println(runeService.addUrl(eachId.get().image))
        }
    }

     */
}