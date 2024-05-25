package com.example.veryinterestingtest.presentation.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
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
import com.example.veryinterestingtest.util.debounce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepo: SearchUseCase,
    private val connectivityManager: ConnectivityManager?
) : BaseViewModel() {

    private val _screenState: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState.Empty)
    val screenState: StateFlow<SearchScreenState> = _screenState

    private var lastQuery: String? = null

    private val searchRequest: (String) -> Unit = debounce(SEARCH_DELAY, viewModelScope, true) { query ->
        if (query.isNotBlank()) {
            if (!checkInternetConnection()) {
                _screenState.value = SearchScreenState.Error("No internet connection")
            } else {
                _screenState.value = SearchScreenState.Loading
                lastQuery = query
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        searchRepo.search(query = SearchQuery(query), page = 1)
                            .cachedIn(viewModelScope)
                            .firstOrNull()?.let {
                                _screenState.value = SearchScreenState.Results(it)
                            }
                    } catch (e: Exception) {
                        _screenState.emit(SearchScreenState.Error(e.message?: "Something went wrong"))
                    }
                }
            }
        }
    }

    fun search(query: String) {
        searchRequest(query)
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

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}