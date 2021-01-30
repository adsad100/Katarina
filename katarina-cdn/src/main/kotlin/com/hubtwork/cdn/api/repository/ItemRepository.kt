package com.hubtwork.cdn.api.repository

import com.hubtwork.cdn.api.domain.Item
import com.hubtwork.cdn.api.domain.Version
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Int>{
    @Query("select id from item", nativeQuery = true)
    fun findAllItemId(): List<Int>
}