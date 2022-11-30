package com.example.data.repositories

import com.example.data.datasources.api.CartDataSource
import com.example.data.datasources.api.DetailsDataSource
import com.example.data.datasources.api.ExplorerDataSource
import com.example.domain.api.repositories.ProductRepository
import com.example.domain.impl.entities.ProductDetail
import com.example.domain.impl.entities.ProductExplorer
import com.example.domain.impl.entities.UserBasket

class ProductRepositoryImpl(
    private val cartDataSource: CartDataSource,
    private val detailsDataSource: DetailsDataSource,
    private val explorerDataSource: ExplorerDataSource
): ProductRepository {
    override suspend fun getProductExplorer(): ProductExplorer? {
        return explorerDataSource.getExplorerData()
    }

    override suspend fun getUserBasket(userId: Int): UserBasket? {
        return cartDataSource.getCartData()
    }

    override suspend fun getProductDetail(productId: Int): ProductDetail? {
        return detailsDataSource.getDetailData()
    }
}