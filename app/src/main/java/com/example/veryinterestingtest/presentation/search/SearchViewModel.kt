package com.example.veryinterestingtest.presentation.search

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.presentation.base.BaseViewModel
import com.example.veryinterestingtest.presentation.search.state.SearchScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel(
    private val searchRepo: SearchUseCase
) : BaseViewModel() {

    private val _screenState: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState.Empty)
    val screenState: StateFlow<SearchScreenState> = _screenState

    init {
        // TODO("Check whether there are saved search results in db")
    }

    suspend fun search() {
        _screenState.value = SearchScreenState.Loading
        searchRepo.search(SearchQuery("Кот")).fold(
            onSuccess = {
                Log.wtf("AAAAA", "Success: ${ it.first().title }")
                _screenState.value = SearchScreenState.Results(it)
            },
            onFailure = {
                Log.wtf("AAAAA", "Error: ${ it.message }")
                _screenState.value = SearchScreenState.Error(it.message ?: "Error")
            }
        )
    }

    fun openInWeb(context: Context, url: String) {
        val customTabsIntent = CustomTabsIntent.Builder()

        val color = ContextCompat.getColor(context, R.color.elements)
        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(color)
            .build()
        customTabsIntent.setDefaultColorSchemeParams(colorSchemeParams)

        val intent = customTabsIntent.build()
        intent.launchUrl(context, Uri.parse(url))
    }

    fun showFullScreen() {
        navigate(R.id.action_searchFragment_to_imageFragment)
    }
}