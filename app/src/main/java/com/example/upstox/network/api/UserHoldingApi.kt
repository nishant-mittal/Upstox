package com.example.upstox.network.api

import com.example.upstox.network.response.ResponseDto
import com.example.upstox.util.Constants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserHoldingApi {
    @GET("/")
    suspend fun getUserHolding(): Response<ResponseDto?>

    companion object {
        fun create(): UserHoldingApi {
            return Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(UserHoldingApi::class.java)
        }
    }
}