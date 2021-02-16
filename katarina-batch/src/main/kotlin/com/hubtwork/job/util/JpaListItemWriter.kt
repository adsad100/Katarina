package com.hubtwork.katarina.batchmatch.batch.util

import org.springframework.batch.item.database.JpaItemWriter

class JpaListItemWriter<T>(
    jpaItemWriter: JpaItemWriter<T>
)
    : JpaItemWriter<List<T>>()
{
    private val writer = jpaItemWriter

    override fun write(items: MutableList<out List<T>>) {
        // LOGIC
        // 리스트 아이템을 모두 Processor 로 부터 전달 받은 후 "중복제거" 후 병합하여 Writing
        var allItems = mutableSetOf<T>()
        items.forEach { allItems.addAll(it) }
        writer.write(allItems.toList())
    }
}