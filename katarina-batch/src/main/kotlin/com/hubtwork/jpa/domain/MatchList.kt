package com.hubtwork.jpa.domain

import javax.persistence.*

@Entity
@Table(name = "matchList")
class MatchList (
    matchId: Long,
    isScanned: Boolean,
        ){

    @Id
    @Column(name = "matchId")
    val matchId: Long = matchId

    @Column(name = "matchData", columnDefinition = "Text")
    var matchData: String? = null

    @Column(name = "isScanned")
    var isScanned: Boolean = isScanned

}