package com.hubtwork.riot.dto.api.v4.match

data class ParticipantIdentityDTO(

    var player: PlayerDTO,      //  플레이어 정보
    var participantId: Int      //  참가자 매핑 ID

)
