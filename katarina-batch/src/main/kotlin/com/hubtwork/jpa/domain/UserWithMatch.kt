package com.hubtwork.jpa.domain

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "search_usermatch")
@IdClass(ComposableKey::class)
class UserWithMatch (
    accountId: String,
    matchId: Long,
    queueType: Int,
    matchEndTime: Long
        ){

    @Id
    @Column(name="accountId", length = 100)
    var accountId: String = accountId

    @Id
    @Column(name="matchId")
    var matchId: Long = matchId

    @Column(name="queueType")
    var queueType: Int = queueType

    /**
     * gameCreation : millisecond
     * gameDuration : second
     */
    @Column(name="matchEndTime")
    var matchTime: LocalDateTime = LocalDateTime.ofInstant(
        Date(matchEndTime).toInstant(),
        ZoneId.of("Asia/Seoul") )

}