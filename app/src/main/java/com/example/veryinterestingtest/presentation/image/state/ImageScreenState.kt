package com.example.veryinterestingtest.presentation.image.state

import com.example.veryinterestingtest.core.entity.Image

sealed class ImageScreenState {

    data object Empty : ImageScreenState()
    data class Data(val image: Image) : ImageScreenState()
    data class Error(val message: String) : ImageScreenState()
}