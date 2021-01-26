package com.hubtwork.katarinaapi.service.riot.`interface`

import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchTimelineDTO
import com.hubtwork.katarinaapi.dto.riotapi.v4.match.MatchlistDTO
import reactor.core.publisher.Mono

interface MatchV4 {

    fun getMatchById(matchId: Long): Mono<MatchDTO>?
    fun getMatchTimelineById(matchId: String): Mono<MatchTimelineDTO>?

    /**
     * Get matchlist for games played on given account ID and platform ID and filtered using given filter parameters, if any.
     *
     * @param champion List<Int> 챔피언 ID
     * @param queue List<Int> 큐 ID
     * @param beginTime Long 시작 시간
     * @param endTime Long 종료 시간 ( beginTime ~ endTime Max duration : 1 week )
     * @param beginIndex Long 시작 인덱스 ( 0 ~ )
     * @param endIndex Long 종료 인덱스 ( beginIndex ~ endIndex Max range : 100 matches )
     *
     */
    fun getMatchListByAccountId(encryptedAccountId: String): Mono<MatchlistDTO>?

    fun getMatchListWithIndexRange100(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>?
    fun getMatchListWithIndexRange20(encryptedAccountId: String, beginIndex: Int): Mono<MatchlistDTO>?

    fun getMatchListWithSeason(encryptedAccountId: String, season: Int, beginIndex: Int): Mono<MatchlistDTO>?
    fun getMatchListWithQueue(encryptedAccountId: String, queue: Int, beginIndex: Int): Mono<MatchlistDTO>?

}