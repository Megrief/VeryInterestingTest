package com.example.veryinterestingtest.presentation.search.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import com.example.veryinterestingtest.core.entity.Image

class ImageAdapter(
    private val openInWebListener: (Context, String) -> Unit,
    private val showFullScreenListener: () -> Unit
    ) : PagingDataAdapter<Image, ImageViewHolder>(ImageCallback()) {
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
        setOnClickListeners(
            openInWeb = {
                item?.run {
                    openInWebListener(holder.itemView.context, link)
                } ?: Toast.makeText(holder.itemView.context, "No link", Toast.LENGTH_SHORT).show()
            },
            showFullScreen = {
                showFullScreenListener()
            }
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder.build(parent)

}