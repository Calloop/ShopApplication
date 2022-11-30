package com.example.shopapplication.fragments.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.usecases.GetUserBasketUseCase
import com.example.domain.impl.entities.UserBasket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(
    private val getCartUseCase: GetUserBasketUseCase
) : ViewModel() {
    private val _error = MutableLiveData<String>().apply { value = null }
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<UserBasket>().apply { value = null }
    val data: LiveData<UserBasket> = _data

    fun loadingData(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val cartData = getCartUseCase.invoke(userId)

                if (cartData == null) {
                    withContext(Dispatchers.Main) {
                        _error.postValue("Data not found")
                    }
                }

                withContext(Dispatchers.Main) {
                    _data.postValue(cartData)
                }
            }
        }
    }

    fun productCounterPlus(position: Int) {
        val basket = _data.value!!

        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                basket.products[position].count++
            }

            _data.postValue(
                basket.copy(
                    totalCost = basket.totalCost + basket.products[position].price
                )
            )
        }
    }

    fun deleteProduct(position: Int) {
        val basket = _data.value!!

        viewModelScope.launch {
            val editProducts = basket.products.toMutableList()

            withContext(Dispatchers.Default) {
                editProducts.removeAt(position)
            }

            _data.postValue(
                basket.copy(
                    products = editProducts,
                    totalCost = basket.totalCost -
                            (basket.products[position].count * basket.products[position].price)
                )
            )
        }
    }

    fun productCountMinus(position: Int) {
        val basket = _data.value!!

        if (basket.products[position].count > 1) {
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    basket.products[position].count--
                }

                _data.postValue(
                    basket.copy(
                        totalCost = basket.totalCost - basket.products[position].price
                    )
                )
            }
        }
    }

    companion object {
        const val fakeUserId = 0
    }
}