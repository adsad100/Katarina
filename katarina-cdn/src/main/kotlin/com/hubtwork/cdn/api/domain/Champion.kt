package com.hubtwork.cdn.api.domain

import javax.persistence.*


@Entity
@Table(name = "champion")
class Champion(
        id: Int,
        name: String,
        image: String,
        title: String
) {
    @Id
    @Column(name= "champion_id")
    var id = id

    @Column(name = "name")
    var name = name

    @Column(name = "image")
    var image = image

    @Column(name = "title")
    var title = title

}