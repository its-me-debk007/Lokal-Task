package com.example.lokaltask.repository

import com.example.lokaltask.model.ProductResponse
import com.example.lokaltask.network.ApiService
import com.example.lokaltask.util.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override fun getData(): Flow<ApiState<ProductResponse>> = flow {
        emit(ApiState.Loading())

        emit(ApiState.Success(data = apiService.getData()))

    }.catch { e ->
        emit(ApiState.Error(msg = e.message.toString()))
    }
}