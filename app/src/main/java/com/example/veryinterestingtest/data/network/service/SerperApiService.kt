package com.example.veryinterestingtest.data.network.service

import com.example.veryinterestingtest.data.network.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SerperApiService {

    @GET("/images?hl=ru&gl=ru&num=100")
    suspend fun search(
        @Query("q") query: String,
        @Query("page") page: Int
    ): Response<ApiResponse>
}