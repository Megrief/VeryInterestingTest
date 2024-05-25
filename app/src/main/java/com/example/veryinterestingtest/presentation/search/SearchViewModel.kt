package com.example.veryinterestingtest.presentation.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.presentation.base.BaseViewModel
import com.example.veryinterestingtest.presentation.search.state.SearchScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull

class SearchViewModel(
    private val searchRepo: SearchUseCase,
    private val connectivityManager: ConnectivityManager?
) : BaseViewModel() {

    private val _screenState: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState.Empty)
    val screenState: StateFlow<SearchScreenState> = _screenState

    suspend fun search(query: String) {
        _screenState.value = SearchScreenState.Loading
        if (!checkInternetConnection()) {
            _screenState.value = SearchScreenState.Error("No internet connection")
            return
        }

        try {
            Log.wtf("AAAAA", "In search vm")
            searchRepo.search(query = SearchQuery(query), page = 1)
                .cachedIn(viewModelScope)
                .firstOrNull()?.let {
                    _screenState.value = SearchScreenState.Results(it)
                }
        } catch (e: Exception) {
            _screenState.emit(SearchScreenState.Error(e.message?: "Something went wrong"))
        }
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

    private fun checkInternetConnection() = connectivityManager
        ?.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
}