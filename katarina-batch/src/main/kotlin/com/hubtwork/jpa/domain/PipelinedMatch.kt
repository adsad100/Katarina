package com.hubtwork.jpa.domain

import javax.persistence.*

@Entity
@Table(name = "pipelinedMatch", indexes = [ Index(columnList = "season"), Index(columnList = "queueType") ])
class PipelinedMatch (
    matchId: Long,
    season: Int,
    queueType: Int,
    matchEndTime: Long,
    matchData: String
){

    @Id
    @Column(name = "matchId")
    val matchId: Long = matchId

    @Column(name = "season")
    val season: Int = season

    @Column(name = "queueType")
    val queueType: Int = queueType

    @Column(name = "matchEndTime")
    val matchEndTime: Long = matchEndTime

    @Column(name = "matchData", columnDefinition = "Text")
    val matchData: String = matchData

}