package com.example.veryinterestingtest.data.network.client

import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.data.network.dto.ApiResponse

interface NetworkClient {
    suspend fun search(query: SearchQuery): Result<ApiResponse>
}