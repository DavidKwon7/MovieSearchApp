package com.flow.searchlist.mapper


import com.flow.common.Mapper
import com.flow.domain.entity.SearchEntityModel
import com.flow.searchlist.model.SearchUiModel
import javax.inject.Inject

class SearchUiDomainMapper @Inject constructor() :
    Mapper<SearchUiModel, SearchEntityModel> {
    override fun from(i: SearchUiModel?): SearchEntityModel {
        return SearchEntityModel(
            id = i?.id,
            title = i?.title
        )
    }

    override fun to(o: SearchEntityModel?): SearchUiModel {
        return SearchUiModel(
            id = o?.id,
            title = o?.title
        )
    }
}