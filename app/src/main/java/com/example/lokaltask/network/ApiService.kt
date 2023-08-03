package com.example.lokaltask.network

import com.example.lokaltask.model.ProductResponse
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getData(): ProductResponse
}