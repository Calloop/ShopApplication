package com.example.domain.impl.usecases

import com.example.domain.api.repositories.ProductRepository
import com.example.domain.api.usecases.GetUserBasketUseCase
import com.example.domain.impl.entities.UserBasket


class GetUserBasketUseCaseImpl(
    private val productRepository: ProductRepository
): GetUserBasketUseCase {
    override suspend fun invoke(userId: Int): UserBasket? {
        return productRepository.getUserBasket(userId)
    }

}