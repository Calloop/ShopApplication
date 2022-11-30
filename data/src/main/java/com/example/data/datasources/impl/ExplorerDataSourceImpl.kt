package com.example.data.datasources.impl

import android.util.Log
import com.example.data.api.ExplorerResponse
import com.example.data.datasources.api.ExplorerDataSource
import com.example.data.mappers.ExplorerEntityMapper
import com.example.domain.impl.entities.ProductExplorer

class ExplorerDataSourceImpl (
    private val explorerResponse: ExplorerResponse,
    private val mapper: ExplorerEntityMapper
): ExplorerDataSource {
    override suspend fun getExplorerData(): ProductExplorer? {
        return try {
            val productData = explorerResponse.getExplorerData()
            mapper.toProductExplorer(productData)
        } catch (e: Exception) {
            Log.d(TAG, e.message!!)
            null
        }
    }

    companion object {
        const val TAG = "DetailsDS"
    }

}