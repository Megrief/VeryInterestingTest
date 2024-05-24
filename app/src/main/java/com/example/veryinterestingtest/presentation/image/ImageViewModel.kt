package com.example.veryinterestingtest.presentation.image

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.veryinterestingtest.core.entity.SearchQuery
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.presentation.base.BaseViewModel
import com.example.veryinterestingtest.presentation.image.state.ImageScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImageViewModel(private val searchRepo: SearchUseCase) : BaseViewModel() {

    private val _screenState: MutableStateFlow<ImageScreenState> = MutableStateFlow(ImageScreenState.Empty)
    val screenState: StateFlow<ImageScreenState> = _screenState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepo.search(SearchQuery("apple")).fold(
                onSuccess = {
                    Log.wtf("AAAAA", "Success: ${ it.first().title }")
                    _screenState.value = ImageScreenState.Data(it.first())
                },
                onFailure = {
                    Log.wtf("AAAAA", "Error: ${ it.message }")
                    _screenState.value = ImageScreenState.Error(it.message ?: "Error")
                }
            )
        }
    }

    fun openInWeb(context: Context) {
        val customTabsIntent = CustomTabsIntent.Builder()

        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(Color.RED)
            .build()

        customTabsIntent.setDefaultColorSchemeParams(colorSchemeParams)
        val intent = customTabsIntent.build()

        if (screenState.value is ImageScreenState.Data) {
            val uri = Uri.parse((screenState.value as ImageScreenState.Data).image.link)
            intent.launchUrl(context, uri)
        }
    }

    fun loadImage(view: ImageView) {
        if (screenState.value is ImageScreenState.Data) {
            Glide.with(view)
                .load((screenState.value as ImageScreenState.Data).image.imageUrl)
                .transform(CenterCrop())
                .into(view)
        }
    }
}