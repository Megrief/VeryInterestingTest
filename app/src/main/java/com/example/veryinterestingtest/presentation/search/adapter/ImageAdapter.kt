package com.example.veryinterestingtest.presentation.search.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.veryinterestingtest.core.entity.Image

class ImageAdapter(
    private val openInWebListener: (Context, String) -> Unit,
    private val showFullScreenListener: () -> Unit
    ) : RecyclerView.Adapter<ImageViewHolder>() {

    private val images = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder.build(parent)

    override fun getItemCount(): Int =
        images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) = with(holder) {
        bind(images[position])
        setOnClickListeners(
            openInWeb = {
                openInWebListener(holder.itemView.context, images[position].link)
            },
            showFullScreen = {
                showFullScreenListener()
            }
        )
    }

    fun setList(newImages: List<Image>) {
        val callback = ImageCallback(images, newList = newImages)
        val diffResult = DiffUtil.calculateDiff(callback)
        images.clear()
        images.addAll(newImages)
        diffResult.dispatchUpdatesTo(this)
    }
}