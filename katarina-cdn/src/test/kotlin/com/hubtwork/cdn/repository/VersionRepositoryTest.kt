package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*
import javax.transaction.Transactional


class VersionRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var versionRepository: VersionRepository

    @Test
    fun create(){

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val championVersion = realm.n["champion"]
        val itemVersion = realm.n["item"]
        val runeVersion = realm.n["rune"]
        val summonerVersion = realm.n["summoner"]
        val masteryVersion = realm.n["mastery"]
        val profileiconVersion = realm.n["profileicon"]
        val mapVersion = realm.n["map"]
        val languageVersion = realm.n["language"]
        val stickerVersion = realm.n["sticker"]
        val latestVersion = realm.v

        versionRepository.deleteAll()

        val version = Version(
            id = 1,
            platform = Platform.KR.localeName,
            latestVersion = latestVersion,
            item = itemVersion.toString(),
            rune = runeVersion.toString(),
            mastery = masteryVersion.toString(),
            summoner = summonerVersion.toString(),
            champion = championVersion.toString(),
            profileicon = profileiconVersion.toString(),
            map = mapVersion.toString(),
            language = languageVersion.toString(),
            sticker = stickerVersion.toString())

        versionRepository.save(version)
    }

    @Test
    fun read(){

        val version: Optional<Version> = versionRepository.findById(1)

        version.ifPresent {
            println("버전 id : " + it.vid)
            println("버전 플랫폼 : " + it.platform)
            println("버전 아이템 : " + it.item)
            println("버전 룬 : " + it.rune)
            println("버전 마스터리 : " + it.mastery)
            println("버전 서머너 : " + it.summoner)
            println("버전 챔피언 : " + it.champion)
            println("버전 프로필 아이콘 : " + it.profileicon)
            println("버전 맵 : " + it.map)
            println("버전 랭귀지 : " + it.language)
            println("버전 스티커 : " + it.sticker)

            Assertions.assertNotNull(version)
        }

    }

    @Test
    fun update(){

    }

    @Test
    @Transactional
    fun delete(){
        val version: Optional<Version> = versionRepository.findById(1)

        Assertions.assertTrue(version.isPresent)

        version.ifPresent {
            versionRepository.delete(it)
        }

        val deleteVersion: Optional<Version> = versionRepository.findById(1)

        Assertions.assertFalse(deleteVersion.isPresent)
    }
}