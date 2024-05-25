package com.example.veryinterestingtest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.data.network.client.NetworkClient
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val networkClient: NetworkClient) : SearchUseCase {

    override suspend fun search(query: SearchQuery, page: Int): Flow<PagingData<Image>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ImagePagingSource(
                query = query,
                networkClient = networkClient
            )
        }
    ).flow


}