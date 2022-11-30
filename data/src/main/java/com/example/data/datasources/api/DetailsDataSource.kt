package com.example.data.datasources.api

import com.example.domain.impl.entities.ProductDetail

interface DetailsDataSource {
    suspend fun getDetailData(): ProductDetail?
}