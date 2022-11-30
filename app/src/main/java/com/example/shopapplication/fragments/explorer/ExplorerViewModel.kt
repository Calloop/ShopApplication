package com.example.shopapplication.fragments.explorer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.usecases.GetExplorerDataUseCase
import com.example.domain.impl.entities.ProductSeller
import com.example.domain.impl.entities.delegate.Delegate
import com.example.domain.impl.entities.delegate.data.CategoryDelegateImpl
import com.example.domain.impl.entities.delegate.data.HotDelegateImpl
import com.example.domain.impl.entities.delegate.data.SearchDelegateImpl
import com.example.domain.impl.entities.delegate.data.SellerDelegateImpl
import com.example.shopapplication.model.categories.Category
import com.example.shopapplication.model.categories.getCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExplorerViewModel(
    private val getExplorerDataUseCase: GetExplorerDataUseCase
) : ViewModel() {
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<List<Delegate>>(emptyList())
    val data: LiveData<List<Delegate>> = _data

    fun loadingData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val explorerData = getExplorerDataUseCase.invoke()
                val categoryData = getCategories()

                if (explorerData == null) {
                    withContext(Dispatchers.Main) {
                        _error.postValue("Data not found")
                    }
                } else {
                    val data = listOf(
                        CategoryDelegateImpl(
                            id = categoryData.hashCode(),
                            categoryData = categoryData
                        ),
                        SearchDelegateImpl(id = _error.hashCode()),
                        HotDelegateImpl(
                            id = explorerData.productsHot.hashCode(),
                            hotData = explorerData.productsHot
                        ),
                        SellerDelegateImpl(
                            id = explorerData.productSellers.hashCode(),
                            sellerData = explorerData.productSellers
                        )
                    )

                    withContext(Dispatchers.Main) {
                        _data.postValue(data)
                    }
                }
            }
        }
    }

    fun setFavorite(position: Int) {
        viewModelScope.launch {
            val dataList = _data.value!!.toMutableList()

            ((dataList[3] as SellerDelegateImpl).sellerData[position] as ProductSeller).isFavorite =
                !((dataList[3] as SellerDelegateImpl).sellerData[position] as ProductSeller).isFavorite

            _data.postValue(dataList)
        }
    }

    fun changeCategory(position: Int) {
        viewModelScope.launch {
            val dataList = _data.value!!.toMutableList()

            (_data.value!![0] as CategoryDelegateImpl).categoryData.forEachIndexed { index, _ ->
                ((dataList[0] as CategoryDelegateImpl).categoryData[index] as Category).isClicked =
                    index == position
            }

            _data.postValue(dataList)
        }
    }
}