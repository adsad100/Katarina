package com.hubtwork.cdn.model.dto

/**
 *          Image DTO
 *
 *          1. ITEM
 *          http://ddragon.leagueoflegends.com/cdn/{ data dragon version }/img/item/{ Item URI }
 *
 *
 */

data class Image (
    var full: String,       // Item URI
    var sprite: String,
    var group: String,      // 이미지 분류

    // loc? 보통 0,0
    var x: Int,
    var y: Int,
    // w: 가로, h: 세로
    var w: Int,
    var h: Int
)
{
    fun getImageURI(): String = "$group/$full"
}