package com.chauxdevapps.catwalletapp.ui.catlist

import com.chauxdevapps.catwalletapp.domain.model.Cat

data class CatListUiState(
    val isLoading: Boolean = false,
    val cats: List<Cat> = emptyList(),
    val error: String? = null
)
