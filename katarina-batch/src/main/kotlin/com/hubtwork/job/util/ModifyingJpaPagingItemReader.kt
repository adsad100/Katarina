package com.hubtwork.katarina.batchmatch.batch.util

import org.springframework.batch.item.database.JpaPagingItemReader

class ModifyingJpaPagingItemReader<T> : JpaPagingItemReader<T>() {

    // 매 Page 를 0 으로 초기화하여 다시 Read 할 때, 문제 없도록 확인
    override fun getPage(): Int {
        return 0
    }
}