package com.example.veryinterestingtest.presentation.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.veryinterestingtest.core.entity.Image

class ImageCallback(
    private val oldList: List<Image>,
    private val newList: List<Image>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] === newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}