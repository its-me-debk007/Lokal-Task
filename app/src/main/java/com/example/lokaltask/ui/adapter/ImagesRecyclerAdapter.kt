package com.example.lokaltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lokaltask.R
import com.example.lokaltask.databinding.ItemProductBinding

class ImagesRecyclerAdapter(
    private val images: List<String>,
    private val context: Context
) : RecyclerView.Adapter<ImagesRecyclerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                removeVisibility(title, brand, discount, price, rating)
                img.scaleType = ImageView.ScaleType.FIT_XY
            }
        }

        private fun removeVisibility(vararg view: View) {
            view.forEach { it.isVisible = false }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context)
            .load(images[position])
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.binding.img)
    }
}