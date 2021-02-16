package com.hubtwork.jpa.repository

import com.hubtwork.jpa.domain.Summoner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface SummonerRepository: JpaRepository<Summoner, String>
{
    @Query("select count(*) from summoner", nativeQuery = true)
    fun countAllSummoners() : BigInteger

    @Query("select count(distinct accountId) from summoner", nativeQuery = true)
    fun countAllSummonersDistinct() : BigInteger
}