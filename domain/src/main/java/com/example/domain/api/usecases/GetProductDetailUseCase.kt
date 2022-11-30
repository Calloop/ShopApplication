package com.example.domain.api.usecases

import com.example.domain.impl.entities.ProductDetail

interface GetProductDetailUseCase {
    suspend fun invoke(productId: Int): ProductDetail?
}