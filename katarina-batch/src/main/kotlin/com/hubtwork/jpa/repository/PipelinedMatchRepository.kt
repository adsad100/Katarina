package com.hubtwork.jpa.repository

import com.hubtwork.jpa.domain.PipelinedMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface PipelinedMatchRepository: JpaRepository<PipelinedMatch, Long> {

    @Query("select count(*) from pipelinedMatch", nativeQuery = true)
    fun countAllMatches() : BigInteger

    @Query("select count(distinct matchId) from pipelinedMatch", nativeQuery = true)
    fun countAllMatchesDistinct() : BigInteger

    @Query("select count(*) from pipelinedMatch where queueType = :queueType", nativeQuery = true)
    fun countAllMatchesByQueueType(@Param("queueType")queueType: Int) : BigInteger

    @Query("select count(*) from pipelinedMatch where season = :season", nativeQuery = true)
    fun countAllMatchesBySeason(@Param("season")season: Int) : BigInteger

}