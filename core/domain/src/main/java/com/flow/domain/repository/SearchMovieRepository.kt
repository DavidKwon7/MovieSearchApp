package com.flow.domain.repository

import androidx.paging.PagingData
import com.flow.domain.entity.MovieSearchEntityModel
import kotlinx.coroutines.flow.Flow

interface SearchMovieRepository {
    fun searchMovie(query: String): Flow<PagingData<MovieSearchEntityModel>>
}