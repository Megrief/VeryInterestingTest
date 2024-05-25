package com.example.veryinterestingtest.data.util

import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.data.network.dto.ImageDto

fun ImageDto.toImage(): Image = Image(
    title = this.title,
    imageUrl = this.imageUrl,
    position = this.position,
    link = this.link
)
