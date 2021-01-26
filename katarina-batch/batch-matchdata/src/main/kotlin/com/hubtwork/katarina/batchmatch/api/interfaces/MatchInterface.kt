package com.hubtwork.katarina.batchmatch.api.interfaces

import com.hubtwork.katarina.batchmatch.api.domain.MatchData
import org.springframework.transaction.annotation.Transactional

interface MatchInterface {

    @Transactional
    fun create(match: MatchData)

    @Transactional(readOnly = true)
    fun read(id: Long): MatchData?

    fun update()

    fun delete()

}