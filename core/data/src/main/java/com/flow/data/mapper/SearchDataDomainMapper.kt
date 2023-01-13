package com.flow.data.mapper

import com.flow.common.Mapper
import com.flow.data.model.SearchDataModel
import com.flow.domain.entity.SearchEntityModel
import javax.inject.Inject

class SearchDataDomainMapper @Inject constructor() :
    Mapper<SearchDataModel, SearchEntityModel> {
    override fun from(i: SearchDataModel?): SearchEntityModel {
        return SearchEntityModel(
            id = i?.id,
            title = i?.title
        )
    }

    override fun to(o: SearchEntityModel?): SearchDataModel {
        return SearchDataModel(
            id = o?.id,
            title = o?.title
        )
    }
}