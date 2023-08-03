package com.example.lokaltask.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.lokaltask.R
import com.example.lokaltask.databinding.FragmentHomeBinding
import com.example.lokaltask.ui.adapter.ProductRecyclerAdapter
import com.example.lokaltask.ui.viewmodel.MyViewModel
import com.example.lokaltask.util.ApiState
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        lifecycleScope.launch(Dispatchers.IO) { getResp() }
    }

    private suspend fun getResp() {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getData().collectLatest {
                withContext(Dispatchers.Main) {
                    when (it) {
                        is ApiState.Loading -> {
                            Log.d("RETRO", "Loading")
                            binding.progressBar.isVisible = true
                            binding.errorTextView.isVisible = false
                            binding.productRecyclerView.isVisible = false
                        }

                        is ApiState.Error -> {
                            Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
                            binding.errorTextView.isVisible = true
                            binding.progressBar.isVisible = false
                            binding.productRecyclerView.isVisible = false
                        }

                        is ApiState.Success -> {
                            Log.d("RETRO", it.data!!.toString())

                            binding.progressBar.isVisible = false
                            binding.errorTextView.isVisible = false
                            binding.productRecyclerView.isVisible = true
                            binding.productRecyclerView.adapter =
                                ProductRecyclerAdapter(
                                    it.data!!.products,
                                    requireContext()
                                ) { product ->
                                    val action =
                                        HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                                            product
                                        )
                                    findNavController().navigate(action)
                                }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}