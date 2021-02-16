package com.hubtwork.jpa.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "summoner")
class Summoner (
    platform: String,
    accountId: String,
    summonerId: String
        )
{

    @Id
    @Column(name="accountId", length = 100)
    var accountId: String = accountId

    @Column(name = "platformId")
    var platform: String = platform

    @Column(name = "summonerId")
    var summonerId: String = summonerId

    // Dummy Date for LRU Algorithm
    @Column(name = "last_scanned")
    var lastScanTime: LocalDateTime = LocalDateTime.of(2010, 12, 27, 12, 0)
}