package com.example.veryinterestingtest.presentation.image

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.core.usecases.SearchUseCase
import com.example.veryinterestingtest.presentation.base.BaseViewModel
import com.example.veryinterestingtest.presentation.image.state.ImageScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImageViewModel(
    private val searchRepo: SearchUseCase,
) : BaseViewModel() {

    private val _screenState: MutableStateFlow<ImageScreenState> = MutableStateFlow(ImageScreenState.Empty)
    val screenState: StateFlow<ImageScreenState> = _screenState

    fun openInWeb(context: Context) {
        val customTabsIntent = CustomTabsIntent.Builder()
        val color = ContextCompat.getColor(context, R.color.elements)

        val colorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(color)
            .build()

        customTabsIntent.setDefaultColorSchemeParams(colorSchemeParams)
        val intent = customTabsIntent.build()

        if (screenState.value is ImageScreenState.Results) {
            val uri = Uri.parse((screenState.value as ImageScreenState.Results).image.link)
            intent.launchUrl(context, uri)
        }
    }

}