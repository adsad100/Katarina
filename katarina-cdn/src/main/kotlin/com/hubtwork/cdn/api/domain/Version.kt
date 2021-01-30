package com.hubtwork.cdn.api.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "versions")
class Version (
    id: Int,
    latestVersion: String,
    platform: String,
    item: String,
    rune: String,
    mastery: String,
    summoner: String,
    champion: String,
    profileicon: String,
    map: String,
    language: String,
    sticker: String
        ){

    @Id
    @Column(name = "id")
    val vid: Int = id

    @Column(name = "latest_version")
    var latestVersion: String = latestVersion

    @Column(name = "platform")
    var platform: String = platform

    @Column(name = "ver_item")
    var item: String = item

    @Column(name = "ver_rune")
    var rune: String = rune

    @Column(name = "ver_mastery")
    var mastery: String = mastery

    @Column(name = "ver_summoner")
    var summoner: String = summoner

    @Column(name = "ver_champion")
    var champion: String = champion

    @Column(name = "ver_profileicon")
    var profileicon: String = profileicon

    @Column(name = "ver_map")
    var map: String = map

    @Column(name = "ver_language")
    var language: String = language

    @Column(name = "ver_sticker")
    var sticker: String = sticker

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = LocalDateTime.now()

}