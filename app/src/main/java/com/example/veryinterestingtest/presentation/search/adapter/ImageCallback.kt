package com.example.veryinterestingtest.presentation.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.veryinterestingtest.core.entity.Image

class ImageCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem.position == newItem.position

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
        oldItem == newItem

}