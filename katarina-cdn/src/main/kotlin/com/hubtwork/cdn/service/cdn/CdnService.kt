package com.hubtwork.cdn.service.cdn

import com.hubtwork.cdn.constant.Locale
import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.cdn.champion.Champion
import com.hubtwork.cdn.model.dto.cdn.champion.ChampionList
import com.hubtwork.cdn.model.dto.cdn.champion.ChampionKeyId
import com.hubtwork.cdn.model.dto.cdn.item.Item
import com.hubtwork.cdn.model.dto.cdn.item.ItemKey
import com.hubtwork.cdn.model.dto.cdn.item.ItemList
import com.hubtwork.cdn.model.dto.cdn.runesreforged.Runes
import com.hubtwork.cdn.model.dto.cdn.summonerspell.SummonerSpellList
import com.hubtwork.cdn.model.dto.cdn.championdetail.ChampionDetail
import com.hubtwork.cdn.model.dto.cdn.championdetail.ChampionDetailInfo
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

class CdnService(private val riotClient: RestTemplate) {

    companion object{
        const val uri_all_champion = "championFull.json"
        const val uri_champion = "champion/"
        const val uri_summoner_spell = "summoner.json"

        const val uri_all_item = "item.json"
        const val uri_all_rune = "runesReforged.json"
        const val uri_all_summonerspell = "summoner.json"
    }

    //       Champion           ------------------------------------------------------------

    /**
     *      모든 챔피언 데이터 조회 ( 요약 정보 )
     *
     *
     */

    // work
    fun getAllChampions(platform: Platform, locale: Locale, version: String): ResponseEntity<ChampionList>? =
        riotClient.getForEntity(platform.getUrlCdn(version, locale) + uri_all_champion, ChampionList::class.java)

    /**
     *      단일 챔피언 데이터 조회   by ID ( 상세 정보 )
     *
     */

    // work
    fun getChampion(platform: Platform, locale: Locale, version: String, championId: String): ResponseEntity<ChampionDetail>? =
        riotClient.getForEntity(platform.getUrlCdn(version, locale) + uri_champion + "$championId.json", ChampionDetail::class.java )

    /**
     *      단일 챔피언 데이터 조회   by Name ( 요약 정보 )
     *
     *
     */

    // work
    fun getChampionByName(platform: Platform, locale: Locale, version: String, championName: String): ResponseEntity<ChampionDetailInfo> {
        val championKeyIdPair = getChampionKeyIdPairs(platform, locale, version)
        val selectedChampionKey =
            championKeyIdPair?.filter { championKeyId -> championKeyId.name.equals(championName) }
                ?.getOrNull(0)?.id
                .let {
                    val allChampions = getAllChampions(platform, locale, version)?.body?.data
                    return ResponseEntity<ChampionDetailInfo>(allChampions?.get(it), HttpStatus.OK)
                }
        return ResponseEntity<ChampionDetailInfo>(HttpStatus.NOT_FOUND)
    }

    /**
     *      Champion Key-Id Pair 이용,
     *
     *      챔피언 key, Id 이용 다른 API 값에서의 매핑 용이
     *
     */

    // work
    private fun getChampionKeyIdPairs(platform: Platform, locale: Locale, version: String): ArrayList<ChampionKeyId>? {
        val result = getAllChampions(platform, locale, version)
        result?.let {
            val championShorts = result.body?.data
            val championShortList = ArrayList(championShorts?.values)
            val championKeyIdPairs = ArrayList<ChampionKeyId>()

            if ( championShortList != null ) {

                for ( champion in championShortList ) {
                    val championKey = champion.key?.toInt()
                    val championId = champion.id
                    val championName = champion.name
                    championKeyIdPairs.add(ChampionKeyId(championKey, championId, championName))
                }

            }
            championKeyIdPairs.sortWith( compareBy { it.key } )

            return championKeyIdPairs
        }
        return null
    }


    /**
     *      모든 아이템 데이터 조회
     *
     */

    // work
    fun getAllItems(platform: Platform, locale: Locale, version: String): ResponseEntity<ItemList> =
        riotClient.getForEntity(platform.getUrlCdn(version, locale) + uri_all_item, ItemList::class.java)

    /**
     *      KEY - ITEM PAIR
     *
     *      TODO - 아래와 같이 매핑 후 DB 에 아이템 데이터 삽입 시 key 를 PK 로 활용
     *
     *
     */
    // work
    private fun getItemKeyPairs(platform: Platform, locale: Locale, version: String): ArrayList<ItemKey>? {
        val result = getAllItems(platform, locale, version)
        result?.let {
            val items = result.body?.data
            val itemKeyPairs = ArrayList<ItemKey>()
            if (items != null) {
                val keys = items?.keys
                for ( key in keys ) {
                    val itemKey = key.toInt()
                    val itemName = items[key]?.name
                    itemKeyPairs.add(ItemKey(itemKey, itemName))
                }
            }

            return itemKeyPairs
        }
        return null
    }

    // work
    fun getItemByKey(platform: Platform, locale: Locale, version: String, itemKey: Int): ResponseEntity<Item> {
        val allItems = getAllItems(platform, locale, version)?.body
        val selectedItem = allItems?.data?.get(itemKey)
            .let {
                return ResponseEntity<Item>(it, HttpStatus.OK)
            }
        return ResponseEntity<Item>(HttpStatus.NOT_FOUND)
    }

    // work
    fun getItemByName(platform: Platform, locale: Locale, version:String, itemName: String): ResponseEntity<Item> {
        val allItems = getAllItems(platform, locale, version)?.body
        val itemKeyPairs = getItemKeyPairs(platform, locale, version)
        val selectedItemKeyPair =
            itemKeyPairs?.filter { selectedItem -> selectedItem.name.equals(itemName) }
                ?.getOrNull(0)

        val selectedItem = allItems?.data?.get(selectedItemKeyPair?.key)
            .let {
                return ResponseEntity<Item>(it, HttpStatus.OK)
            }
        return ResponseEntity<Item>(HttpStatus.NOT_FOUND)
    }

    // work
    fun getRunes(platform: Platform, locale: Locale, version: String): ResponseEntity<ArrayList<Runes>> =
        riotClient.exchange(platform.getUrlCdn(version, locale) + uri_all_rune, HttpMethod.GET, null, typeReference<ArrayList<Runes>>())
    // work
    fun getSummonerSpells(platform: Platform, locale: Locale, version: String): ResponseEntity<SummonerSpellList> =
        riotClient.getForEntity(platform.getUrlCdn(version, locale) + uri_all_summonerspell, SummonerSpellList::class)

}


// ParameterizedTypeReference :: Array 형태의 JSON Object 매핑
inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}