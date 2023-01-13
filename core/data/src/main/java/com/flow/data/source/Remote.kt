package com.flow.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.flow.data.remote.MovieSearchAPI
import javax.inject.Inject

class Remote @Inject constructor(
    val api: MovieSearchAPI
) {

    fun searchMovie(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviePagingSource(api, query) }
        ).flow
}