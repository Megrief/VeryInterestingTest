package com.example.veryinterestingtest.app.di

import com.example.veryinterestingtest.presentation.image.ImageViewModel
import com.example.veryinterestingtest.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<SearchViewModel> {
        SearchViewModel(get())
    }

    viewModel {
        ImageViewModel(get())
    }
}