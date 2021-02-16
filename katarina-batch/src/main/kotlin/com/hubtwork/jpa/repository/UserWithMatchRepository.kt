package com.hubtwork.jpa.repository

import com.hubtwork.jpa.domain.UserWithMatch
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface UserWithMatchRepository: JpaRepository<UserWithMatch, String>
{
    @Query("select count(*) from search_usermatch", nativeQuery = true)
    fun countAllMatchesWithUser() : BigInteger

    @Query("select count(distinct matchId, accountId) from search_usermatch", nativeQuery = true)
    fun countAllMatchesWithUserDistinct() : BigInteger

    @Query("select * from search_usermatch where accountId= :targetSummoner order by matchEndTime", nativeQuery = true)
    fun getMatchesByAccountId(@Param("targetSummoner")accountId: String): List<UserWithMatch>

    @Query("select * from search_usermatch where accountId= :targetSummoner order by matchEndTime limit 1", nativeQuery = true)
    fun getLastMatchByAccountId(@Param("targetSummoner")accountId: String): UserWithMatch

}