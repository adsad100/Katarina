package com.hubtwork.cdn.api.domain

import org.springframework.data.jpa.repository.Query
import javax.persistence.*

@Entity
@Table(name= "rune")
class Rune(
        id: Int,
        runeTypeEN: String,
        runeTypeKR: String,
        nameEN: String,
        nameKR: String,
        isMain: Boolean,
        // shortDescription: String,
        longDescription: String,
        image: String,
) {
    @Id
    @Column(name = "rune_id")
    val id = id

    @Column(name = "rune_type_EN")
    val runeTypeEnglish = runeTypeEN

    @Column(name = "rune_type_KR")
    val runeTypeKorean = runeTypeKR

    @Column(name = "name_EN")
    val nameEnglish = nameEN

    @Column(name = "name_KR")
    val nameKorean = nameKR

    @Column(name = "is_main", columnDefinition = "BOOLEAN")
    val isMain = isMain

    // @Column(name = "short_description", columnDefinition = "TEXT")
    // val shortDescription = shortDescription

    @Column(name = "long_description", columnDefinition = "TEXT")
    val longDescription = longDescription

    @Column(name = "image")
    var image = image

}