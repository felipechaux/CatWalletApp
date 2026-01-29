package com.chauxdevapps.catwalletapp.ui.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chauxdevapps.catwalletapp.domain.model.Resource
import com.chauxdevapps.catwalletapp.domain.usecase.GetCatListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchCats()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getCatListUseCase()) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, cats = result.data) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
