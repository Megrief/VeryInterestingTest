package com.example.veryinterestingtest.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.data.network.client.NetworkClient
import com.example.veryinterestingtest.data.network.dto.ImageDto
import com.example.veryinterestingtest.data.util.toImage

class ImagePagingSource(
    private val networkClient: NetworkClient,
    private val query: SearchQuery
) : PagingSource<Int, Image>() {

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val currentPage = params.key?: 1
        val result = networkClient.search(
            page = currentPage,
            query = query
        )
        return result.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.images.map(ImageDto::toImage),
                    prevKey = if (currentPage > 1) currentPage - 1 else null,
                    nextKey = if (it.images.isNotEmpty()) currentPage + 1 else null
                )
            },
            onFailure = {
                LoadResult.Error(it)
            }
        )
    }

}