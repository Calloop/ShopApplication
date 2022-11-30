package com.example.domain.impl.usecases

import com.example.domain.api.repositories.ProductRepository
import com.example.domain.api.usecases.GetProductDetailUseCase
import com.example.domain.impl.entities.ProductDetail

class GetProductDetailUseCaseImpl(
    private val productRepository: ProductRepository
): GetProductDetailUseCase {
    override suspend fun invoke(productId: Int): ProductDetail? {
        return productRepository.getProductDetail(productId)
    }

}