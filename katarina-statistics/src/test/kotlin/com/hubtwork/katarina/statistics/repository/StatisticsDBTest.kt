package com.hubtwork.katarina.statistics.repository

import com.hubtwork.katarina.statistics.StatisticsApplicationTests
import com.hubtwork.katarina.statistics.domain.matchlist.MatchPlayerDTO
import com.hubtwork.katarina.statistics.matcherapi.domain.UserWithMatch
import com.hubtwork.katarina.statistics.matcherapi.repository.UserWithMatchRepository
import com.hubtwork.katarina.statistics.service.MatcherService
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaNormalMatch
import com.hubtwork.katarina.statistics.statisticsapi.domain.StaSoloRank
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaNormalMatchRepository
import com.hubtwork.katarina.statistics.statisticsapi.repository.StaSoloRankRepository
import com.hubtwork.katarina.statistics.statisticsapi.service.StaService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import javax.transaction.Transactional

@Configuration
@EnableAutoConfiguration
@ComponentScan
class StatisticsDBTest: StatisticsApplicationTests() {

    @Autowired
    lateinit var userWithMatchRepository: UserWithMatchRepository

    @Autowired
    lateinit var matcherService: MatcherService

    @Autowired
    lateinit var staService: StaService

    @Test
    fun insertStatisticsDBTest() {
        //target summoner's account id
        val targetAccount: String = "wzGWVnvYOOcKsUrTgvlTuuM7KEp1VWj8OtSYKFtCtvE"

        val matchAndQType: List<UserWithMatch> = userWithMatchRepository.getMatchesByAccountId(targetAccount)
        matchAndQType.forEach {
            val eachMatch = matcherService.matchReader(it.matchId, it.queueType)

            println(eachMatch?.matchId)
            when(it.queueType) {
                420 -> staService.insertSoloRank(eachMatch,targetAccount)
                430 -> staService.insertNormalMatch(eachMatch,targetAccount)
                440 -> staService.insertFlexRank(eachMatch,targetAccount)
                450 -> staService.insertARAM(eachMatch,targetAccount)
                else -> staService.insertEventMatch(eachMatch,targetAccount)
                }
            staService.insertDuoInfo(eachMatch, targetAccount)
            }
        }

    @Test
    fun insertDuoInfoTest(){
        val targetAccount: String = "wzGWVnvYOOcKsUrTgvlTuuM7KEp1VWj8OtSYKFtCtvE"

        val matchAndQType: List<UserWithMatch> = userWithMatchRepository.getMatchesByAccountId(targetAccount)
        matchAndQType.forEach {
            val eachMatch = matcherService.matchReader(it.matchId, it.queueType)

            println(eachMatch?.matchId)

            staService.insertDuoInfo(eachMatch, targetAccount)
        }
    }
}
