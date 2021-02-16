package com.hubtwork.riot.dto.api.v4.platformdata

data class StatusDTO(

    var id: Int,
    var maintenance_status: String?,
    var incident_severity: String?,
    var titles: ArrayList<ContentDTO>?,
    var updates: ArrayList<UpdateDTO>?,

    var created_at: String?,
    var archive_at: String?,
    var updated_at: String?,

    var platforms: ArrayList<String>?

)
