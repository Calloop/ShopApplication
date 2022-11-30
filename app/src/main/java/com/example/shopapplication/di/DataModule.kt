package com.example.shopapplication.di

import com.example.data.api.CartResponse
import com.example.data.api.DetailsResponse
import com.example.data.api.ExplorerResponse
import com.example.data.datasources.api.CartDataSource
import com.example.data.datasources.api.DetailsDataSource
import com.example.data.datasources.api.ExplorerDataSource
import com.example.data.datasources.impl.CartDataSourceImpl
import com.example.data.datasources.impl.DetailsDataSourceImpl
import com.example.data.datasources.impl.ExplorerDataSourceImpl
import com.example.data.mappers.CartEntityMapper
import com.example.data.mappers.DetailEntityMapper
import com.example.data.mappers.ExplorerEntityMapper
import com.example.data.repositories.ProductRepositoryImpl
import com.example.domain.api.repositories.ProductRepository
import com.example.shopapplication.BuildConfig.BASE_URL
import org.koin.dsl.module

val dataModule = module {
    single<CartDataSource> {
        CartDataSourceImpl(
            cartResponse = CartResponse.create(BASE_URL),
            mapper = CartEntityMapper()
        )
    }

    single<DetailsDataSource> {
        DetailsDataSourceImpl(
            detailsResponse = DetailsResponse.create(BASE_URL),
            mapper = DetailEntityMapper()
        )
    }

    single<ExplorerDataSource> {
        ExplorerDataSourceImpl(
            explorerResponse = ExplorerResponse.create(BASE_URL),
            mapper = ExplorerEntityMapper()
        )
    }

    single<ProductRepository> {
        ProductRepositoryImpl(
            cartDataSource = get(),
            detailsDataSource = get(),
            explorerDataSource = get()
        )
    }
}