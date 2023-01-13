package com.flow.search.mapper

import com.flow.common.Mapper
import com.flow.data.model.MovieSearchEntityModel
import com.flow.search.model.MovieSearchUiModel
import javax.inject.Inject

class MovieSearchUiDomainMapper @Inject constructor() :
    Mapper<MovieSearchUiModel, MovieSearchEntityModel> {
    override fun from(i: MovieSearchUiModel?): MovieSearchEntityModel {
        return MovieSearchEntityModel(
            image = i?.image,
            link = i?.link,
            pubDate = i?.pubDate,
            title = i?.title,
            userRating = i?.userRating
        )
    }

    override fun to(o: MovieSearchEntityModel?): MovieSearchUiModel {
        return MovieSearchUiModel(
            image = o?.image,
            link = o?.link,
            pubDate = o?.pubDate,
            title = o?.title,
            userRating = o?.userRating
        )
    }
}