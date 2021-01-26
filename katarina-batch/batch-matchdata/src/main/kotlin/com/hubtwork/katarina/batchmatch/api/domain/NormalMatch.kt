package com.hubtwork.katarina.batchmatch.api.domain

import javax.persistence.*

@Entity
@Table(name = "normaldraft")
class NormalMatch (
    matchId: Long,
    sason: Int,
    matchJson: String,
        ){

    @Id
    @Column(name = "matchId")
    var id: Long = matchId

    @Column(name = "matchJson", columnDefinition = "TEXT")
    var matchData: String = matchJson

}