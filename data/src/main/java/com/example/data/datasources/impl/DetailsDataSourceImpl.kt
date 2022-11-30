package com.example.data.datasources.impl

import android.util.Log
import com.example.data.api.DetailsResponse
import com.example.data.datasources.api.DetailsDataSource
import com.example.data.mappers.DetailEntityMapper
import com.example.domain.impl.entities.ProductDetail

class DetailsDataSourceImpl(
    private val detailsResponse: DetailsResponse,
    private val mapper: DetailEntityMapper
): DetailsDataSource {
    override suspend fun getDetailData(): ProductDetail? {
        return try {
            val productData = detailsResponse.getDetailsData()
            return mapper.toProductDetail(productData)
        } catch (e: Exception) {
            Log.d(TAG, e.message!!)
            null
        }
    }

    companion object {
        const val TAG = "DetailsDS"
    }

}