package com.example.veryinterestingtest.data.repository

import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.data.network.client.NetworkClient
import com.example.veryinterestingtest.data.network.dto.ImageDto
import com.example.veryinterestingtest.util.toImage

class SearchRepository(private val networkClient: NetworkClient) : SearchUseCase {

    override suspend fun search(query: SearchQuery): Result<List<Image>> =
        networkClient.search(query).fold(
            onSuccess = {
                Result.success(it.images.map(ImageDto::toImage))
            },
            onFailure = {
                Result.failure(it)
            }
        )
}