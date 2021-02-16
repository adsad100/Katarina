package com.hubtwork.jpa.repository

import com.hubtwork.jpa.domain.MatchList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface MatchListRepository: JpaRepository<MatchList, Long> {

    fun existsByMatchId(matchId: Long) : Boolean

    @Query("select count(*) from matchList", nativeQuery = true)
    fun countAllMatches() : BigInteger

    @Query("select count(distinct matchId) from matchList", nativeQuery = true)
    fun countAllMatchesDistinct() : BigInteger

    @Query("select count(*) from matchList where matchData = null and isScanned = 0", nativeQuery = true)
    fun countAllMatchesJustEnrolledFromAPI() : BigInteger

    @Query("select count(*) from matchList where matchData = null", nativeQuery = true)
    fun countAllMatchesIsNotMappedFromAPI() : BigInteger

    @Query("select count(*) from matchList where isScanned = 0", nativeQuery = true)
    fun countAllMatchesIsNotScanned() : BigInteger


}