package com.flow.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.flow.domain.entity.Item
import com.flow.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private var _searchStateFlow = MutableStateFlow<SearchState>(SearchState.Empty)
    val searchStateFlow: StateFlow<SearchState> get() = _searchStateFlow

    fun searchMovie(query: String) =
        viewModelScope.launch {
            _searchStateFlow.value = SearchState.Loading
            searchMovieUseCase.invoke(query)
                .cachedIn(viewModelScope)
                .catch { e ->
                    _searchStateFlow.value = SearchState.Failed(e)
                }.collect { searchData ->
                    _searchStateFlow.value = SearchState.Success(flowOf(searchData))
                }
        }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}


sealed class SearchState() {
    object Empty: SearchState()
    object Loading: SearchState()
    class Success(var data: Flow<PagingData<Item>>) : SearchState()
    class Failed(var message: Throwable) : SearchState()
}

