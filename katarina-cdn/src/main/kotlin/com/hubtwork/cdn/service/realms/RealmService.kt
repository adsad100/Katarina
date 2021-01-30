package com.hubtwork.cdn.service.realms

import com.hubtwork.cdn.constant.Platform
import com.hubtwork.cdn.model.dto.Realm
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

class RealmService(private val restTemplate: RestTemplate) {


    fun getRealm(platform: Platform): ResponseEntity<Realm> =
        restTemplate.getForEntity(platform.getUrlRealms(), Realm::class.java)




}


// ParameterizedTypeReference :: Array 형태의 JSON Object 매핑
inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}