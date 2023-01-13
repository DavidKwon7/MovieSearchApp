package com.flow.searchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flow.domain.usecase.GetAllSearchUseCase
import com.flow.searchlist.mapper.SearchUiDomainMapper
import com.flow.searchlist.model.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val searchAllSearchUseCase: GetAllSearchUseCase,
    private val searchUiDomainMapper: SearchUiDomainMapper
) : ViewModel() {

    private var _searchStateFlow = MutableStateFlow<SearchState>(SearchState.Empty)
    val searchStateFlow: StateFlow<SearchState> get() = _searchStateFlow


    fun getAllSearch() =
        viewModelScope.launch {
            _searchStateFlow.value = SearchState.Loading
            searchAllSearchUseCase.invoke()
                .catch { e ->
                    _searchStateFlow.value = SearchState.Failed(e)
                }.collectLatest { search ->
                    _searchStateFlow.value = SearchState.Success(
                        searchUiDomainMapper.toList(search)
                    )
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
    class Success(var data: List<SearchUiModel>) : SearchState()
    class Failed(var message: Throwable) : SearchState()
}