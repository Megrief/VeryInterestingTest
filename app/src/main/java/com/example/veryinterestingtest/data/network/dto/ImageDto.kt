package com.example.veryinterestingtest.data.network.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("domain")
    val domain: String,
    @SerializedName("googleUrl")
    val googleUrl: String,
    @SerializedName("imageHeight")
    val imageHeight: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("imageWidth")
    val imageWidth: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("source")
    val source: String,
    @SerializedName("thumbnailHeight")
    val thumbnailHeight: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("thumbnailWidth")
    val thumbnailWidth: Int,
    @SerializedName("title")
    val title: String
)