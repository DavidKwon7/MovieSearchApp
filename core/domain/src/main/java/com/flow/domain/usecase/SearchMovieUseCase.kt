package com.flow.domain.usecase

import androidx.paging.PagingData
import com.flow.domain.entity.MovieSearchEntityModel
import com.flow.domain.qualifiers.IoDispatcher
import com.flow.domain.repository.SearchMovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: SearchMovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun invoke(query: String): Flow<PagingData<MovieSearchEntityModel>> {
        return repository.searchMovie(query)
            .flowOn(dispatcher)
    }
}