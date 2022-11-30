package com.example.data.mappers

import com.example.data.entities.cart.BasketItems
import com.example.data.entities.cart.Cart
import com.example.domain.impl.entities.ProductBasket
import com.example.domain.impl.entities.UserBasket

class CartEntityMapper {
    fun toUserBasket(cart: Cart): UserBasket {
        val products = cart.basketItems.map { toProductBasket(it) }

        return UserBasket(
            id = cart.userId,
            products = products.removeDuplicates(),
            deliveryCost = cart.deliveryPrice,
            totalCost = cart.totalPrice
        )
    }

    private fun toProductBasket(product: BasketItems): ProductBasket {
        return ProductBasket(
            id = product.id,
            name = product.name,
            pictureUrl = product.pictureUrl,
            price = product.price,
            count = 1
        )
    }

    private fun List<ProductBasket>.removeDuplicates(): List<ProductBasket> {
        val tempList: MutableList<ProductBasket> = this.toMutableList()
        val removeIndexes = mutableListOf<Int>()

        this.forEachIndexed { index, product ->
            removeIndexes.clear()

            for (tempIndex in index + 1..tempList.lastIndex) {
                if (product.name == tempList[tempIndex].name) {
                    removeIndexes.add(tempIndex)
                    tempList[index].count++
                }
            }

            if (removeIndexes.isNotEmpty()) removeIndexes.forEach { tempList.removeAt(it) }
        }

        return tempList
    }
}