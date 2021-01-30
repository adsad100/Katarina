package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.Rune
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RuneRepository: JpaRepository<Rune, Int> {
    @Query("select rune_id from rune", nativeQuery = true)
    fun findAllRuneId(): List<Int>
}