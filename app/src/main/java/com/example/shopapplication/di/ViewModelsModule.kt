package com.example.shopapplication.di

import com.example.shopapplication.fragments.cart.CartViewModel
import com.example.shopapplication.fragments.details.DetailsViewModel
import com.example.shopapplication.fragments.explorer.ExplorerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel<CartViewModel> { CartViewModel(getCartUseCase = get()) }

    viewModel<DetailsViewModel> { DetailsViewModel(getProductDetailUseCase = get()) }

    viewModel<ExplorerViewModel> { ExplorerViewModel(getExplorerDataUseCase = get()) }
}