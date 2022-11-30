package com.example.domain.api.usecases

import com.example.domain.impl.entities.ProductExplorer

interface GetExplorerDataUseCase {
    suspend fun invoke(): ProductExplorer?
}