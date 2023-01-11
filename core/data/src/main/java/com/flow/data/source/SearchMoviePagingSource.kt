package com.flow.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flow.common.Constants.Companion.PAGING_START_PAGE
import com.flow.data.remote.MovieSearchAPI
import com.flow.domain.entity.Item
import retrofit2.HttpException
import java.io.IOException

class SearchMoviePagingSource(
    private val api: MovieSearchAPI,
    private val query: String
): PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: PAGING_START_PAGE
        return try {
            val response = api.searchMovie(
                query = query,
                start =  position,
                display = params.loadSize
            )
            val movie = response.items

            LoadResult.Page(
                data = movie,
                prevKey = if (position == PAGING_START_PAGE) null else position -1,
                nextKey = if (movie.isEmpty()) null else position +1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }


}