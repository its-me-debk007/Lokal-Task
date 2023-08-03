package com.example.lokaltask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lokaltask.model.ProductResponse
import com.example.lokaltask.repository.Repository
import com.example.lokaltask.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getData(): StateFlow<ApiState<ProductResponse>> =
        repository.getData().stateIn(
            scope = viewModelScope,
            initialValue = ApiState.Loading(),
            started = SharingStarted.WhileSubscribed(5000)
        )
}