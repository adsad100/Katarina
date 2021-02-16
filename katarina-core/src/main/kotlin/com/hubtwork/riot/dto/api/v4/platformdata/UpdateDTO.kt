package com.hubtwork.riot.dto.api.v4.platformdata

data class UpdateDTO(
    var id: Int,
    var author: String?,
    var publish: Boolean?,
    var publish_locations: ArrayList<String>?,
    var translations: ArrayList<ContentDTO>?,
    var created_at: String?,
    var updated_at: String

)
