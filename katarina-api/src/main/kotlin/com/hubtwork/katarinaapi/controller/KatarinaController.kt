package com.hubtwork.katarinaapi.controller

import com.hubtwork.katarinaapi.dto.katarina.user.SummonerInfoDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.summoners.SummonerDTO
import com.hubtwork.katarinaapi.exception.ErrorResponse
import com.hubtwork.katarinaapi.service.katarina.KatarinaApiService
import com.hubtwork.katarinaapi.service.riot.RiotApiService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api")
class KatarinaController(private val katarinaApiService: KatarinaApiService) {

    @GetMapping("/{summoner_Name}")
    fun getMatchDetails(@PathVariable("summoner_Name") name: String) =
        katarinaApiService.getRecentMatchDetailsBySummonerName(name)

    @GetMapping("/summoner/{summonerName}")
    fun getSummonerData(@PathVariable("summonerName") name: String): ResponseEntity<Any> =
        katarinaApiService.getSummonerData(name)

    @GetMapping("/matches/{encryptedAccountId}")
    fun getSummonerMatches(@PathVariable("encryptedAccountId") accountId: String): ResponseEntity<Any> =
        katarinaApiService.getMatchRecords(accountId)


}