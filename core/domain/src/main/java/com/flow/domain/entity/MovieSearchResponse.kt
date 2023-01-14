package com.flow.domain.entity


data class MovieSearchResponse(
    val display: Int?,
    val items: List<Item>,
    val lastBuildDate: String?,
    val start: Int?,
    val total: Int?
)