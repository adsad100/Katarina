package com.hubtwork.katarinaapi.dto.katarina.matchlist

import com.hubtwork.katarina.batchmatch.domain.katarina.matchlist.KatarinaMatchDTO

data class KatarinaMatchListDTO(

    var matchCount: Int,
    var matches: List<KatarinaMatchDTO>?,

    )
