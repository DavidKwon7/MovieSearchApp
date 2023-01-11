package com.flow.domain.entity


data class MovieSearchResponse(
    //@SerializedName("display")
    val display: Int?,
    //@SerializedName("items")
    val items: List<Item>,
    //@SerializedName("lastBuildDate")
    val lastBuildDate: String?,
    //@SerializedName("start")
    val start: Int?,
    //@SerializedName("total")
    val total: Int?
)