package com.example.veryinterestingtest.presentation.search

import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.presentation.base.BaseViewModel

class SearchViewModel(
    private val searchRepository: SearchUseCase
) : BaseViewModel() {

}