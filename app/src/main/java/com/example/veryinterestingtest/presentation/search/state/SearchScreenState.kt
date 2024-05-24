package com.example.veryinterestingtest.presentation.search.state

import com.example.veryinterestingtest.core.entity.Image

sealed class SearchScreenState {
    data object Empty : SearchScreenState()
    data object Loading : SearchScreenState()
    data class Results(val images: List<Image>) : SearchScreenState()
    data class Error(val message: String) : SearchScreenState()
}