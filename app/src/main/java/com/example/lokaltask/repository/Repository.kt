package com.example.lokaltask.repository

import com.example.lokaltask.model.ProductResponse
import com.example.lokaltask.util.ApiState
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getData(): Flow<ApiState<ProductResponse>>
}