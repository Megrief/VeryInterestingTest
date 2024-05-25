package com.example.veryinterestingtest.data.network.client

import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.data.network.dto.ApiResponse
import com.example.veryinterestingtest.data.network.service.SerperApiService
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(private val apiService: SerperApiService) : NetworkClient {

    override suspend fun search(query: SearchQuery, page: Int): Result<ApiResponse> {
        try {
            val response = apiService.search(query = query.query, page = page)

            return Result.success(response)
        } catch (e: IOException) {
            return Result.failure(e)
        } catch (e: HttpException) {
            return Result.failure(e)
        }

    }
}