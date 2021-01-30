package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Champion
import com.hubtwork.cdn.api.domain.Item
import com.hubtwork.cdn.api.exception.NotFoundException
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.ChampionRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class ChampionService(val ddChampionRepository: ChampionRepository, private val restTemplate: RestTemplate) {
    @Transactional
    fun create(champion: Champion) {
        ddChampionRepository.save(champion)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(id: Int): Champion {
        // TODO - ITEM Exception
        return ddChampionRepository.findByIdOrNull(id) ?: throw NotFoundException.DDVersion.get()
    }

    fun update() {

    }

    fun delete() {

    }

    fun championVersionCheck(championVersion: String): Boolean {

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val version = realm.n["champion"].toString()

        return championVersion == version

    }

    fun addUrl(version: String, image: String): String{
        val baseUrl = "http://ddragon.leagueoflegends.com/cdn/$version/img/champion/"

        return baseUrl + image
    }

    //http://ddragon.leagueoflegends.com/cdn/11.1.1/img/champion/Aatrox.png
}