package com.flow.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "searchDataModel"
)
data class SearchEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String?
)
