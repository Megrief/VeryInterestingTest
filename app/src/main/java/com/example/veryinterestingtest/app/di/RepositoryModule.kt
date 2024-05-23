package com.example.veryinterestingtest.app.di

import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.data.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SearchUseCase> {
        SearchRepository(get())
    }
}