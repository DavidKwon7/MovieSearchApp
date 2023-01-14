package com.flow.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flow.domain.entity.Item
import com.flow.domain.usecase.InsertSearchUseCase
import com.flow.domain.usecase.SearchMovieUseCase
import com.flow.search.mapper.SearchUiDomainMapper
import com.flow.search.model.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val insertSearchUseCase: InsertSearchUseCase,
    private val searchUiDomainMapper: SearchUiDomainMapper
) : ViewModel() {

    private var _searchMovieStateFlow = MutableStateFlow<SearchMovieState>(SearchMovieState.Empty)
    val searchMovieStateFlow: StateFlow<SearchMovieState> get() = _searchMovieStateFlow

    fun searchMovie(query: String) =
        viewModelScope.launch {
            _searchMovieStateFlow.value = SearchMovieState.Loading
            searchMovieUseCase.invoke(query)
                .cachedIn(viewModelScope)
                .catch { e ->
                    _searchMovieStateFlow.value = SearchMovieState.Failed(e)
                }.collect { searchData ->
                    _searchMovieStateFlow.value = SearchMovieState.Success(
                        flowOf((searchData))
                    )
                }
        }

    fun insertSearch(search: SearchUiModel) =
        viewModelScope.launch(Dispatchers.IO) {
            val insertData = searchUiDomainMapper.from(search)
            insertSearchUseCase.invoke(insertData)

        }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}


sealed class SearchMovieState() {
    object Empty: SearchMovieState()
    object Loading: SearchMovieState()
    class Success(var data: Flow<PagingData<Item>>) : SearchMovieState()
    class Failed(var message: Throwable) : SearchMovieState()
}
