package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.SummonerSpell
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SummonerSpellRepository : JpaRepository<SummonerSpell, Int> {
    @Query("select spell_id from summoner_spell", nativeQuery = true)
    fun findAllSummonerSpellId(): List<Int>
}