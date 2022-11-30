package com.example.data.datasources.api

import com.example.domain.impl.entities.ProductExplorer

interface ExplorerDataSource {
    suspend fun getExplorerData(): ProductExplorer?
}