package com.example.data.datasources.api

import com.example.domain.impl.entities.UserBasket

interface CartDataSource {
    suspend fun getCartData(): UserBasket?
}