package com.chauxdevapps.catwalletapp.ui.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chauxdevapps.catwalletapp.domain.model.cat.Cat
import com.chauxdevapps.catwalletapp.domain.model.Resource
import com.chauxdevapps.catwalletapp.domain.usecase.payment.CheckUnlimitedAccessUseCase
import com.chauxdevapps.catwalletapp.domain.usecase.cat.GetCatListUseCase
import com.chauxdevapps.catwalletapp.domain.usecase.cat.ToggleFavoriteUseCase
import com.chauxdevapps.catwalletapp.domain.usecase.payment.TokenizePaymentMethodUseCase
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.ERROR_LIMIT_REACHED
import com.chauxdevapps.catwalletapp.domain.util.DomainConstants.ERROR_TOKENIZATION_FAILED
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val checkUnlimitedAccessUseCase: CheckUnlimitedAccessUseCase,
    private val tokenizePaymentMethodUseCase: TokenizePaymentMethodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchCats()
        checkUnlimitedAccess()
    }

    private fun checkUnlimitedAccess() {
        viewModelScope.launch {
            val isUnlimited = checkUnlimitedAccessUseCase()
            _uiState.update { it.copy(isUnlimitedAccess = isUnlimited) }
        }
    }

    fun fetchCats() {
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

    fun toggleFavorite(cat: Cat) {
        viewModelScope.launch {
            val result = toggleFavoriteUseCase(cat)
            if (result.isSuccess) {
                // Update local state to reflect change immediately
                _uiState.update { state ->
                    state.copy(
                        cats = state.cats.map {
                            if (it.id == cat.id) it.copy(isFavorite = !it.isFavorite) else it
                        }
                    )
                }
            } else {
                val error = result.exceptionOrNull()
                if (error?.message == ERROR_LIMIT_REACHED) {
                    _uiState.update { it.copy(showLimitReachedDialog = true) }
                }
            }
        }
    }

    fun dismissLimitDialog() {
        _uiState.update { it.copy(showLimitReachedDialog = false) }
    }

    fun tokenizePaymentMethod(cardNumber: String, cvv: String, expiryDate: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isTokenizing = true, tokenizationError = null) }
            val result = tokenizePaymentMethodUseCase(cardNumber, cvv, expiryDate)
            if (result.isSuccess) {
                _uiState.update { 
                    it.copy(
                        isTokenizing = false, 
                        isUnlimitedAccess = true, 
                        showLimitReachedDialog = false 
                    ) 
                }
            } else {
                _uiState.update { 
                    it.copy(
                        isTokenizing = false, 
                        tokenizationError = ERROR_TOKENIZATION_FAILED
                    ) 
                }
            }
        }
    }
}
