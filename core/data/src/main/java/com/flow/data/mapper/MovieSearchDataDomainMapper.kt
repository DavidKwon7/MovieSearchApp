package com.flow.data.mapper

import com.flow.common.Mapper
import com.flow.data.model.Item
import com.flow.domain.entity.MovieSearchEntityModel
import javax.inject.Inject

class MovieSearchDataDomainMapper @Inject constructor() :
    Mapper<Item, MovieSearchEntityModel> {
    override fun from(i: Item?): MovieSearchEntityModel {
        return MovieSearchEntityModel(
            image = i?.image,
            link = i?.link,
            pubDate = i?.pubDate,
            title = i?.title,
            userRating = i?.userRating
        )
    }

    override fun to(o: MovieSearchEntityModel?): Item {
        return Item(
            image = o?.image,
            link = o?.link,
            pubDate = o?.pubDate,
            title = o?.title,
            userRating = o?.userRating
        )
    }
}