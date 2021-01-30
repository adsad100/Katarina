package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Item
import com.hubtwork.cdn.api.domain.SummonerSpell
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.SummonerSpellRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class SummonerSpellService(val summonerSpellRepository: SummonerSpellRepository, private val restTemplate: RestTemplate) {

    @Transactional
    fun create(summonerSpell: SummonerSpell) {
        summonerSpellRepository.save(summonerSpell)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(id: Int){

    }

    fun update() {

    }

    fun delete() {

    }

    fun summonerVersionCheck(summonerVersion: String): Boolean {

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val version = realm.n["champion"].toString()

        return summonerVersion == version

    }

    fun addUrl(version: String, image: String): String{
        val baseUrl = "http://ddragon.leagueoflegends.com/cdn/$version/img/spell/"

        return baseUrl + image
    }

    //http://ddragon.leagueoflegends.com/cdn/11.1.1/img/spell/SummonerFlash.png
}