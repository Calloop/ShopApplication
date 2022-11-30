package com.example.domain.api.repositories

import com.example.domain.impl.entities.ProductDetail
import com.example.domain.impl.entities.ProductExplorer
import com.example.domain.impl.entities.UserBasket

interface ProductRepository {
    suspend fun getProductExplorer(): ProductExplorer?

    suspend fun getUserBasket(userId: Int): UserBasket?

    suspend fun getProductDetail(productId: Int): ProductDetail?
}