package com.flow.data.mapper

import com.flow.common.Mapper
import com.flow.data.model.SearchEntityModel
import javax.inject.Inject

class SearchDataDomainMapper @Inject constructor() :
    Mapper<SearchEntityModel, com.flow.domain.entity.SearchEntityModel> {
    override fun from(i: com.flow.data.model.SearchEntityModel?): com.flow.domain.entity.SearchEntityModel {
        return com.flow.domain.entity.SearchEntityModel(
            id = i?.id,
            title = i?.title
        )
    }

    override fun to(o: com.flow.domain.entity.SearchEntityModel?): com.flow.data.model.SearchEntityModel {
        return com.flow.data.model.SearchEntityModel(
            id = o?.id,
            title = o?.title
        )
    }
}