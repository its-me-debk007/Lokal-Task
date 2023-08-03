package com.example.lokaltask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lokaltask.R
import com.example.lokaltask.databinding.ItemProductBinding
import com.example.lokaltask.model.Product

class ProductRecyclerAdapter(
    private val data: List<Product>,
    private val context: Context,
    // Using Higher Order Function, since there is only one method. Otherwise, interface would be adequate!
    private val onClick: (Product) -> Unit

) : RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener { onClick(data[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(context)
                .load(data[position].thumbnail)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img)

            title.text = data[position].title
            brand.text = data[position].brand
            discount.text = "${data[position].discountPercentage}% off"
            price.text = "MRP ₹${data[position].price}"
            rating.text = "${data[position].rating} ⭐"
        }
    }
}