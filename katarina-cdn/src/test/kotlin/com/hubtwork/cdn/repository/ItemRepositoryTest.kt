package com.hubtwork.cdn.repository

import com.hubtwork.cdn.CdnApplicationTests
import com.hubtwork.cdn.api.domain.Champion
import com.hubtwork.cdn.api.domain.Item
import com.hubtwork.cdn.api.domain.Version
import com.hubtwork.cdn.api.repository.ItemRepository
import com.hubtwork.cdn.api.repository.VersionRepository
import com.hubtwork.cdn.api.service.ItemService
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

class ItemRepositoryTest: CdnApplicationTests() {

    @Autowired
    lateinit var itemRepository: ItemRepository

    @Autowired
    lateinit var versionRepository: VersionRepository

    @Test
    fun create(){

        val platform = Platform.KR

        val realmService = RealmService(restTemplate)
        val cdnService = CdnService(restTemplate)
        val realm: Realm = realmService.getRealm(platform).body!!

        val itemService = ItemService(itemRepository, restTemplate)

        var itemVersion = versionRepository.getOne(1).item

        val flag = itemService.itemVersionCheck(itemVersion)

        if(!flag) {

            itemRepository.deleteAll()

            val itemList = realm.n["item"]?.let {
                cdnService.getAllItems(platform, Locale.KO_KR, it)
            }

            itemList?.body!!.getItemEntityList().forEach {
                itemService.create(it)
            }

            //todo - test database after platform KR issue complete
            val itemIdList: List<Int> = itemRepository.findAllItemId()
            itemIdList.forEach{
                val eachId: Optional<Item> = itemRepository.findById(it)
                eachId.ifPresent {
                    //todo - code optimization
                    val currentVersion = realm.n["item"].toString()
                    val newImage = itemService.addUrl(currentVersion,eachId.get().image)
                    it.image = newImage
                    itemRepository.save(it)
                }
            }

            itemVersion = realm.n["item"].toString()

            val version: Optional<Version> = versionRepository.findById(1)

            version.ifPresent {
                it.item = itemVersion
                it.updatedAt = LocalDateTime.now()
                versionRepository.save(it)
            }
        }
    }

    @Test
    fun read(){
        val item: Optional<Item> = itemRepository.findById(1001)

        item.ifPresent {
            println("아이템 아이디 : " + it.id)
            println("아이템 이름 : " + it.name)
            println("아이템 description : " + it.description)
            println("아이템 plain text : " + it.plainText)
            println("아이템 full gold : " + it.fullGold)
            println("아이템 own gold : " + it.ownGold)
            println("아이템 icon url : " + it.image)

            Assertions.assertNotNull(item)
        }
    }


    @Test
    @Transactional
    fun delete(){
        val item: Optional<Item> = itemRepository.findById(1001)

        Assertions.assertTrue(item.isPresent)

        item.ifPresent{
            itemRepository.delete(it)
        }

        val deleteItem: Optional<Item> = itemRepository.findById(1001)

        Assertions.assertFalse(deleteItem.isPresent)
    }


}