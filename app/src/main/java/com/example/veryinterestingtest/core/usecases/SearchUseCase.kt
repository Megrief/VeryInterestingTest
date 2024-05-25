package com.example.veryinterestingtest.core.usecases

import androidx.paging.PagingData
import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.core.entity.SearchQuery
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend fun search(query: SearchQuery, page: Int): Flow<PagingData<Image>>
}