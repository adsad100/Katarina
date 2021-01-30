package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.Champion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChampionRepository: JpaRepository<Champion, Int> {
    @Query("select champion_id from champion", nativeQuery = true)
    fun findAllChampionId(): List<Int>
}