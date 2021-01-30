package com.hubtwork.cdn.service.api

import com.hubtwork.cdn.constant.Platform
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity


@Service
class ApiService(private val restTemplate: RestTemplate) {

    companion object{
        const val uri_version = "versions.json"
    }


    fun getVersionList(platform: Platform): ResponseEntity<List<String>> =
        restTemplate.exchange(platform.getUrlApi() + uri_version, HttpMethod.GET, null, typeReference<List<String>>())




}


// ParameterizedTypeReference :: Array 형태의 JSON Object 매핑
inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}