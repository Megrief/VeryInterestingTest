package com.example.veryinterestingtest.core.usecases

import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.core.entity.SearchQuery

interface SearchUseCase {
    suspend fun search(query: SearchQuery): Result<List<Image>>
}