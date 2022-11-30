package com.example.shopapplication.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.usecases.GetProductDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.domain.impl.entities.ProductDetail

class DetailsViewModel(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    private val _loading = MutableLiveData<Boolean>().apply { value = true }
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>().apply { value = null }
    val error: LiveData<String> = _error

    private val _data = MutableLiveData<ProductDetail>().apply { value = null }
    val data: LiveData<ProductDetail> = _data

    fun loadingData(productId: Int) {
        viewModelScope.launch {
            _loading.postValue(true)

            withContext(Dispatchers.IO) {
                val detailsData = getProductDetailUseCase.invoke(productId)

                if (detailsData == null) {
                    withContext(Dispatchers.Main) {
                        _loading.postValue(false)
                        _error.postValue("Data not found")
                    }
                }

                withContext(Dispatchers.Main) {
                    _data.postValue(detailsData)
                    _loading.postValue(false)
                }
            }
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            _data.postValue(_data.value!!.copy(isFavorite = !_data.value!!.isFavorite))
        }
    }

    companion object {
        const val fakeProductId = 0
    }
}