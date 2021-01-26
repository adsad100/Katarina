package com.hubtwork.katarinaapi.service.riot

import com.google.gson.Gson
import com.hubtwork.katarinaapi.config.WebClientConfig
import com.hubtwork.katarinaapi.dto.riotapi.v3.champion.ChampionInfoDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.league.LeagueEntryDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.league.LeagueListDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchTimelineDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchlistDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.platformdata.PlatformDataDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.summoners.SummonerDTO
import com.hubtwork.katarinaapi.service.riot.`interface`.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@Service
class RiotApiService(private val webClient: WebClient, private val gson: Gson)
    : ChampionV3, LeagueV4, LolStatusV4, MatchV4, SummonerV4
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

        fun httpHeader():HttpHeaders {
            val header = HttpHeaders()
            header.set("Date", LocalDateTime.now().toString())
            header.set("Content-Type", "application/json;charset=utf-8")
            return header
        }

        private val logger = LoggerFactory.getLogger(WebClientConfig::class.java)

        fun uriParameterBuilder(params: Map<String, String>): String {
            var uriComponents: UriComponentsBuilder = UriComponentsBuilder.newInstance()
            for ((k, v) in params) {
                uriComponents = uriComponents.queryParam(k, v)
            }
            return uriComponents.build().toUriString()
        }

    }

    private val platform = PlatformRoutes.KR.route

    override fun getChampionRotations(): Mono<ChampionInfoDTO>? =
        webClient.get()
            .uri("https://$platform$champion_rotations")
            .retrieve()
            .bodyToMono(ChampionInfoDTO::class.java)

    override fun getChallengerLeague(queue: String): Mono<LeagueListDTO>? =
        webClient.get()
            .uri("https://$platform$challenger_league$queue")
            .retrieve()
            .bodyToMono(LeagueListDTO::class.java)

    override fun getGrandMasterLeague(queue: String): Mono<LeagueListDTO>? =
        webClient.get()
            .uri("https://$platform$grandmaster_league$queue")
            .retrieve()
            .bodyToMono(LeagueListDTO::class.java)

    override fun getMasterLeague(queue: String): Mono<LeagueListDTO>? =
        webClient.get()
            .uri("https://$platform$master_league$queue")
            .retrieve()
            .bodyToMono(LeagueListDTO::class.java)

    override fun getAllLeague(queue: String, tier: String, division: String): Flux<LeagueEntryDTO>? =
        webClient.get()
            .uri("https://$platform$leagueEntry_by_param$queue/$tier/$division")
            .retrieve()
            .bodyToFlux(LeagueEntryDTO::class.java)

    override fun getLeagueByLeagueId(leagueId: String): Mono<LeagueListDTO>? =
        webClient.get()
            .uri("https://$platform$league_by_leagueId$leagueId")
            .retrieve()
            .bodyToMono(LeagueListDTO::class.java)

    override fun getLeagueBySummonerId(encryptedSummonerId: String): Flux<LeagueEntryDTO>? =
        webClient.get()
            .uri("https://$platform$leagueInfo_by_summonerId$encryptedSummonerId")
            .retrieve()
            .bodyToFlux(LeagueEntryDTO::class.java)

    override fun getPlatformStatus(): Mono<PlatformDataDTO>? =
        webClient.get()
            .uri("https://$platform$platform_data")
            .retrieve()
            .bodyToMono(PlatformDataDTO::class.java)

    override fun getMatchById(matchId: Long): Mono<MatchDTO>? =
        webClient.get()
            .uri("https://$platform$match_by_matchId$matchId")
            .retrieve()
            .bodyToMono(MatchDTO::class.java)

    override fun getMatchTimelineById(matchId: String): Mono<MatchTimelineDTO>? =
        webClient.get()
            .uri("https://$platform$match_timeline_by_matchId$matchId")
            .retrieve()
            .bodyToMono(MatchTimelineDTO::class.java)

    override fun getMatchListByAccountId(encryptedAccountId: String): Mono<MatchlistDTO>? =
        webClient.get()
            .uri("https://$platform$match_by_accountId$encryptedAccountId")
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    override fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri{ uri -> uri
                .path("https://$platform$match_by_accountId$encryptedAccountId")
                .queryParam("beginIndex", beginIndex)
                .build()
            }
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    override fun getMatchListWithIndexRange20(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri{ uri -> uri
                .path("https://$platform$match_by_accountId$encryptedAccountId")
                .queryParam("beginIndex", beginIndex)
                .queryParam("endIndex", beginIndex+19)
                .build()
            }
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    // test method for dev key
    fun getMatchListWithIndexRange5(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri("https://$platform$match_by_accountId$encryptedAccountId?beginIndex=$beginIndex&endIndex=${beginIndex + 5}")
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    // it's for gathering season rank champion filtered data
    override fun getMatchListWithSeason(encryptedAccountId: String, season: Int, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri{ uri -> uri
                .path("https://$platform$match_by_accountId$encryptedAccountId")
                .queryParam("queue", 420)           // Solo Rank
                .queryParam("queue", 430)           // Flex Rank
                .queryParam("season", season)
                .queryParam("beginIndex", beginIndex)
                .build()
            }
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    override fun getMatchListWithQueue(encryptedAccountId: String, queue: Int, beginIndex: Int): Mono<MatchlistDTO>? =
        webClient.get()
            .uri{ uri -> uri
                .path("https://$platform$match_by_accountId$encryptedAccountId")
                .queryParam("queue", queue)
                .queryParam("beginIndex", beginIndex)
                .build()
            }
            .retrieve()
            .bodyToMono(MatchlistDTO::class.java)

    override fun getSummonerByName(summonerName: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_name$summonerName")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerByPUUID(encryptedPUUID: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_puuid$encryptedPUUID")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerByAccountId(encryptedAccountId: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_account$encryptedAccountId")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

    override fun getSummonerBySummonerId(encryptedSummonerId: String): Mono<SummonerDTO>? =
        webClient.get()
            .uri("https://$platform$summoner_by_summonerId$encryptedSummonerId")
            .retrieve()
            .bodyToMono(SummonerDTO::class.java)

}