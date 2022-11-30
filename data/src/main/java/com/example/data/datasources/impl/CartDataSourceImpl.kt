package com.example.data.datasources.impl

import android.util.Log
import com.example.data.api.CartResponse
import com.example.data.datasources.api.CartDataSource
import com.example.data.mappers.CartEntityMapper
import com.example.domain.impl.entities.UserBasket

class CartDataSourceImpl(
    private val cartResponse: CartResponse,
    private val mapper: CartEntityMapper
): CartDataSource {
    override suspend fun getCartData(): UserBasket? {
        return try {
            val cartData = cartResponse.getCartData()
            mapper.toUserBasket(cartData)
        } catch (e: Exception) {
            Log.d(TAG, e.message!!)
            null
        }
    }

    companion object {
        const val TAG = "CartDS"
    }
}