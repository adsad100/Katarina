package com.hubtwork.cdn.api.domain

import javax.persistence.*

@Entity
@Table(name = "item")
class Item(
        id: Int,
        name: String,
        description: String,
        plainText: String,
        ownGold: Int,
        fullGold: Int,
        image: String
)
{
    @Id
    @Column(name = "id")
    var id: Int = id

    @Column(name = "image")
    var image: String = image

    @Column(name = "name")
    var name: String = name

    @Column(name = "description", columnDefinition = "TEXT")
    var description: String = description

    @Column(name = "plaintext")
    var plainText: String = plainText

    @Column(name = "ownGold")
    var ownGold: Int = ownGold

    @Column(name = "fullGold")
    var fullGold: Int = fullGold
}