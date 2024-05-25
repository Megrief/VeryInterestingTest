package com.example.veryinterestingtest.presentation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.veryinterestingtest.R
import com.example.veryinterestingtest.core.entity.Image
import com.example.veryinterestingtest.databinding.ImageCardBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ImageViewHolder(private val binding: ImageCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Image?) {
        MainScope().launch {
            with(binding) {
                setImage(image?.imageUrl)
                title.text = image?.title ?: root.context.getString(R.string.empty)

                root.setOnClickListener {
                    navigationGroup.isGone = !navigationGroup.isGone
                }
            }
        }
    }

    private fun setImage(imageUrl: String?) {
        val cornerRadiusInPx = binding.root.resources.getDimensionPixelSize(R.dimen.margin_medium)

        Glide.with(binding.imageView)
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .transform(CenterCrop(), RoundedCorners(cornerRadiusInPx))
            .into(binding.imageView)
    }

    fun setOnClickListeners(openInWeb: (View) -> Unit, showFullScreen: (View) -> Unit) {
        binding.openInWeb.setOnClickListener(openInWeb)
        binding.showFullscreen.setOnClickListener(showFullScreen)
    }

    companion object {
        fun build(parent: ViewGroup): ImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ImageCardBinding.inflate(inflater, parent, false)
            return ImageViewHolder(binding)
        }
    }
}