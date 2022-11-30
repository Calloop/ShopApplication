package com.example.domain.impl.usecases

import com.example.domain.api.repositories.ProductRepository
import com.example.domain.api.usecases.GetExplorerDataUseCase
import com.example.domain.impl.entities.ProductExplorer


class GetExplorerDataUseCaseImpl(
    private val productRepository: ProductRepository
): GetExplorerDataUseCase {
    override suspend fun invoke(): ProductExplorer? {
        return productRepository.getProductExplorer()
    }
}