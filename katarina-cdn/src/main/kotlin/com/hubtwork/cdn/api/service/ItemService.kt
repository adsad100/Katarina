package com.hubtwork.cdn.api.service

import com.hubtwork.cdn.api.domain.Item
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.exception.NotFoundException
import com.hubtwork.cdn.api.exception.ResponseException
import com.hubtwork.cdn.api.repository.ItemRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import com.hubtwork.cdn.service.realms.RealmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate

@Service
class ItemService(val ddItemRepository: ItemRepository, private val restTemplate: RestTemplate) {

    @Transactional
    fun create(item: Item) {
        ddItemRepository.save(item)
    }

    @Transactional(readOnly = true)
    @Throws(ResponseException::class)
    fun read(id: Int): Item {
        // TODO - ITEM Exception
        return ddItemRepository.findByIdOrNull(id) ?: throw NotFoundException.DDVersion.get()
    }

    fun update() {

    }

    fun delete() {

    }

    fun itemVersionCheck(itemVersion: String): Boolean {

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val version = realm.n["item"].toString()

        return itemVersion == version

    }

    fun addUrl(version: String, image: String): String{
        val baseUrl = "http://ddragon.leagueoflegends.com/cdn/$version/img/item/"

        return baseUrl + image
    }

    //http://ddragon.leagueoflegends.com/cdn/11.1.1/img/item/1001.png
}
