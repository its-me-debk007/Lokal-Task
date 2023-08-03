package com.example.lokaltask.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.lokaltask.R
import com.example.lokaltask.databinding.FragmentProductDetailBinding
import com.example.lokaltask.ui.adapter.ImagesRecyclerAdapter
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding: FragmentProductDetailBinding get() = _binding!!
    private val args by navArgs<ProductDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)
        binding.apply {

            img.adapter = ImagesRecyclerAdapter(args.product.images, requireContext())

            title.text = args.product.title
            brand.text = args.product.brand
            discount.text = "${args.product.discountPercentage}% off"
            price.text = "MRP ₹${args.product.price}"
            rating.text = "${args.product.rating} ⭐"
            description.text = args.product.description
            category.text = "Category: ${args.product.category}"
            stock.text = "Stock: ${args.product.stock}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}