package com.flow.domain.entity

import java.io.Serializable

data class Item(
    val image: String?,
    val link: String?,
    val pubDate: String?,
    val title: String?,
    val userRating: String?
) : Serializable
