package com.hubtwork.katarina.batchmatch.api.domain

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "search_usermatch")
@IdClass(ComposableKey::class)
class UserWithMatch (
    accountId: String,
    matchId: Long
        ){

    @Id
    @Column(name="accountId")
    var accountId: String = accountId

    @Id
    @Column(name="matchId")
    var matchId: Long = matchId

}

class ComposableKey(
    val accountId: String = "",
    val matchId: Long = 0
): Serializable