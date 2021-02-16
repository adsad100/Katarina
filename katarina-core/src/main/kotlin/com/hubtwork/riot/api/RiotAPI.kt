package com.hubtwork.riot.api

import com.hubtwork.katarina.batchmatch.domain.riot.v4.summoner.SummonerDTO
import com.hubtwork.riot.api.interfaces.*
import com.hubtwork.riot.dto.api.v3.champion.ChampionInfoDTO
import com.hubtwork.riot.dto.api.v4.league.LeagueEntryDTO
import com.hubtwork.riot.dto.api.v4.league.LeagueListDTO
import com.hubtwork.riot.dto.api.v4.match.MatchDTO
import com.hubtwork.riot.dto.api.v4.match.MatchListDTO
import com.hubtwork.riot.dto.api.v4.platformdata.PlatformDataDTO
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class RiotAPI(private val webClient: WebClient)
    : MatchV4, SummonerV4, ChampionV3, LeagueV4, LolStatusV4
{
    companion object {

        // v3 - champion
        const val champion_rotations = "/lol/platform/v3/champion-rotations"

        // v4 - status
        const val platform_data = "/lol/status/v4/platform-data"

        // v4 - summoner
        const val summoner_by_name = "/lol/summoner/v4/summoners/by-name/"
        const val summoner_by_account = "/lol/summoner/v4/summoners/by-account/"
        const val summoner_by_puuid = "/lol/summoner/v4/summoners/by-puuid/"
        const val summoner_by_summonerId = "/lol/summoner/v4/summoners/"

        // v4 - league
        const val league_by_leagueId = "/lol/league/v4/leagues/"
        const val leagueInfo_by_summonerId = "/lol/league/v4/entries/by-summoner/"
        const val leagueEntry_by_param = "/lol/league/v4/entries/"

        const val challenger_league = "/lol/league/v4/challengerleagues/by-queue/"
        const val grandmaster_league = "/lol/league/v4/grandmasterleagues/by-queue/"
        const val master_league = "/lol/league/v4/masterleagues/by-queue/"

        // v4 - match
        const val match_by_matchId = "/lol/match/v4/matches/"
        const val match_by_accountId = "/lol/match/v4/matchlists/by-account/"
        const val match_timeline_by_matchId = "/lol/match/v4/timelines/by-match/"

        fun httpHeader(): HttpHeaders {
            val header = HttpHeaders()
            header.set("Date", LocalDateTime.now().toString())
            header.set("Content-Type", "application/json;charset=utf-8")
            return header
        }

        private val logger = LoggerFactory.getLogger(RiotAPI::class.java)

        fun uriParameterBuilder(params: Map<String, String>): String {
            var uriComponents: UriComponentsBuilder = UriComponentsBuilder.newInstance()
            for ((k, v) in params) {
                uriComponents = uriComponents.queryParam(k, v)
            }
            return uriComponents.build().toUriString()
        }

    }

    private val platform = PlatformRoutes.KR.route

    override fun getMatchById(matchId: Long): Mono<MatchDTO> =
        webClient.get()
            .uri("https://$platform$match_by_matchId$matchId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(MatchDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ MatchDetail API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<MatchDTO>()
            }

    override fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchListDTO> =
        webClient.get()
            .uri("https://$platform$match_by_accountId$encryptedAccountId?beginIndex=$beginIndex&endIndex=${beginIndex + 99}")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(MatchListDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ MatchList API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<MatchListDTO>()
            }

    override fun getSummonerByName(summonerName: String): Mono<SummonerDTO> =
        webClient.get()
            .uri("https://$platform$summoner_by_name$summonerName")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(SummonerDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<SummonerDTO>()
            }

    override fun getSummonerByPUUID(encryptedPUUID: String): Mono<SummonerDTO> =
        webClient.get()
            .uri("https://$platform$summoner_by_puuid$encryptedPUUID")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(SummonerDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<SummonerDTO>()
            }

    override fun getSummonerByAccountId(encryptedAccountId: String): Mono<SummonerDTO> =
        webClient.get()
            .uri("https://$platform$summoner_by_account$encryptedAccountId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(SummonerDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<SummonerDTO>()
            }

    override fun getSummonerBySummonerId(encryptedSummonerId: String):  Mono<SummonerDTO> =
        webClient.get()
            .uri("https://$platform$summoner_by_summonerId$encryptedSummonerId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(SummonerDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<SummonerDTO>()
            }

    override fun getChampionRotations(): Mono<ChampionInfoDTO> =
        webClient.get()
            .uri("https://$platform$champion_rotations")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(ChampionInfoDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<ChampionInfoDTO>()
            }

    override fun getChallengerLeague(queue: String): Mono<LeagueListDTO> =
        webClient.get()
            .uri("https://$platform$challenger_league$queue")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(LeagueListDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<LeagueListDTO>()
            }

    override fun getGrandMasterLeague(queue: String): Mono<LeagueListDTO> =
        webClient.get()
            .uri("https://$platform$grandmaster_league$queue")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(LeagueListDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<LeagueListDTO>()
            }

    override fun getMasterLeague(queue: String): Mono<LeagueListDTO> =
        webClient.get()
            .uri("https://$platform$master_league$queue")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(LeagueListDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<LeagueListDTO>()
            }

    override fun getAllLeague(queue: String, tier: String, division: String): Flux<LeagueEntryDTO> =
        webClient.get()
            .uri("https://$platform$challenger_league$queue")
            .exchangeToFlux { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToFlux res.bodyToFlux(LeagueEntryDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToFlux Flux.empty<LeagueEntryDTO>()
            }

    override fun getLeagueByLeagueId(leagueId: String): Mono<LeagueListDTO> =
        webClient.get()
            .uri("https://$platform$league_by_leagueId$leagueId")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(LeagueListDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<LeagueListDTO>()
            }

    override fun getLeagueBySummonerId(encryptedSummonerId: String): Flux<LeagueEntryDTO> =
        webClient.get()
            .uri("https://$platform$league_by_leagueId$encryptedSummonerId")
            .exchangeToFlux { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToFlux res.bodyToFlux(LeagueEntryDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToFlux Flux.empty<LeagueEntryDTO>()
            }

    override fun getPlatformStatus(): Mono<PlatformDataDTO> =
        webClient.get()
            .uri("https://$platform$platform_data")
            .exchangeToMono { res ->
                if (res.statusCode().is2xxSuccessful) {
                    return@exchangeToMono res.bodyToMono(PlatformDataDTO::class.java)
                }
                else if (res.statusCode().is5xxServerError) {
                    logger.warn("[ Summoner API ] 504 GatewayTimeOut Error Received")
                }
                return@exchangeToMono Mono.empty<PlatformDataDTO>()
            }


}