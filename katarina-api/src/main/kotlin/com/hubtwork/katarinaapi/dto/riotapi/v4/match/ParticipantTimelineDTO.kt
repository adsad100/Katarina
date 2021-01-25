package com.hubtwork.katarinaapi.dto.riotapi.v4.match

data class ParticipantTimelineDTO (
    var lane: String,       //  라인
    var participantId: Int, //  참가자 매핑 ID

    var role: String,       // ?

    var csDiffPerMinDeltas: Map<String, Double> = mapOf(),
    var goldPerMinDeltas: Map<String, Double> = mapOf(),
    var xpDiffPerMinDeltas: Map<String, Double> = mapOf(),
    var creepsPerMinDeltas: Map<String, Double> = mapOf(),
    var damageTakenDiffPerMinDeltas: Map<String, Double> = mapOf(),
    var damageTakenPerMinDeltas: Map<String, Double> = mapOf(),


)
{
    fun getLaneData(): String {
        return if(lane == "BOTTOM")
            "$lane ${role.replace("DUO_", "")}"
        else
            "$lane"
    }
}