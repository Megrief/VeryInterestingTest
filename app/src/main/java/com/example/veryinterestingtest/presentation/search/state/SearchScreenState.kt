package com.example.veryinterestingtest.presentation.search.state

import androidx.paging.PagingData
import com.example.veryinterestingtest.core.entity.Image

sealed class SearchScreenState {
    data object Empty : SearchScreenState()
    data object Loading : SearchScreenState()
    data class Results(val images: PagingData<Image>) : SearchScreenState()
    data class Error(val message: String) : SearchScreenState()
}