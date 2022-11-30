package com.example.shopapplication.di

import com.example.data.api.CartResponse
import com.example.data.api.DetailsResponse
import com.example.data.api.ExplorerResponse
import com.example.shopapplication.BuildConfig.BASE_URL
import org.koin.dsl.module

val networkModule = module {
    single<CartResponse> { CartResponse.create(BASE_URL) }
    single<DetailsResponse> { DetailsResponse.create(BASE_URL) }
    single<ExplorerResponse> { ExplorerResponse.create(BASE_URL) }
}