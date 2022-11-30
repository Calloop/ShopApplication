package com.example.data.api

import com.example.data.entities.explorer.Explorer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExplorerResponse {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getExplorerData(): Explorer

    companion object {
        fun create(url: String): ExplorerResponse {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ExplorerResponse::class.java)
        }
    }
}