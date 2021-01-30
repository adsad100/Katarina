package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Rune
import com.hubtwork.cdn.api.exception.NotFoundException
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.RuneRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class RuneService(val ddRuneRepository: RuneRepository, private val restTemplate: RestTemplate){
    @Transactional
    fun create(rune: Rune) {
        ddRuneRepository.save(rune)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(id: Int): Rune {
        // TODO - ITEM Exception
        return ddRuneRepository.findByIdOrNull(id) ?: throw NotFoundException.DDVersion.get()
    }

    fun update() {

    }

    fun delete() {

    }

    fun runeVersionCheck(runeVersion: String): Boolean {

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val version = realm.n["champion"].toString()

        return runeVersion == version

    }

    //https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png

    fun addUrl(image: String): String {
        val baseUrl = "https://ddragon.leagueoflegends.com/cdn/img/"
        return baseUrl + image
    }


}