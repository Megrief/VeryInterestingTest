package com.example.veryinterestingtest.data.network.dto

data class ApiResponse(
    val searchParameters: SearchParameters,
    val images: List<ImageDto>
)