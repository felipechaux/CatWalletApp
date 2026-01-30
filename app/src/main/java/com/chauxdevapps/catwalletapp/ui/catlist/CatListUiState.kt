package com.chauxdevapps.catwalletapp.ui.catlist

import com.chauxdevapps.catwalletapp.domain.model.cat.Cat

data class CatListUiState(
    val isLoading: Boolean = false,
    val cats: List<Cat> = emptyList(),
    val error: String? = null,
    val showLimitReachedDialog: Boolean = false,
    val isUnlimitedAccess: Boolean = false,
    val isTokenizing: Boolean = false,
    val tokenizationError: String? = null
)
