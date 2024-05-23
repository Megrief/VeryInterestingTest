package com.example.veryinterestingtest.data.network.dto

import com.google.gson.annotations.SerializedName

data class SearchParameters(
    @SerializedName("q")
    val q: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("engine")
    val engine: String,
    @SerializedName("num")
    val num: Int
)