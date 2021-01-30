package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.Skill
import com.hubtwork.cdn.api.domain.SkillId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SkillRepository: JpaRepository<Skill, SkillId> {
    //todo - find sql query what to get composite key

    @Query("select distinct champion_id from champion_skill", nativeQuery = true)
    fun findUniqueId(): List<Int>

    @Query("select * from champion_skill where champion_id = :id", nativeQuery = true)
    fun selectAllById(@Param("id") championId: Int): List<Skill>

}