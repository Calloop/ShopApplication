package com.example.data.entities.explorer

import com.google.gson.annotations.SerializedName

data class Explorer(
    @SerializedName("home_store") val homeStore: List<HomeStore>,
    @SerializedName("best_seller") val bestSellers: List<BestSellers>
)
