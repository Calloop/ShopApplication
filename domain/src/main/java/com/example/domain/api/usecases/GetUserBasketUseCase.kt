package com.example.domain.api.usecases

import com.example.domain.impl.entities.UserBasket

interface GetUserBasketUseCase {
    suspend fun invoke(userId: Int): UserBasket?
}