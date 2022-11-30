package com.example.shopapplication.di

import com.example.domain.api.usecases.GetExplorerDataUseCase
import com.example.domain.api.usecases.GetProductDetailUseCase
import com.example.domain.api.usecases.GetUserBasketUseCase
import com.example.domain.impl.usecases.GetExplorerDataUseCaseImpl
import com.example.domain.impl.usecases.GetProductDetailUseCaseImpl
import com.example.domain.impl.usecases.GetUserBasketUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetExplorerDataUseCase> { GetExplorerDataUseCaseImpl(productRepository = get()) }

    factory<GetProductDetailUseCase> { GetProductDetailUseCaseImpl(productRepository = get()) }

    factory<GetUserBasketUseCase> { GetUserBasketUseCaseImpl(productRepository = get()) }
}