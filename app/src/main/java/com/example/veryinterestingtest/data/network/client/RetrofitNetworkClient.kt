package com.example.veryinterestingtest.data.network.client

import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.data.network.dto.ApiResponse
import com.example.veryinterestingtest.data.network.service.SerperApiService

class RetrofitNetworkClient(private val apiService: SerperApiService) : NetworkClient {

    override suspend fun search(query: SearchQuery): Result<ApiResponse> {
        val response = apiService.search(query = query.query, page = 1)

        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable(response.errorBody().toString()))
        }
    }
}