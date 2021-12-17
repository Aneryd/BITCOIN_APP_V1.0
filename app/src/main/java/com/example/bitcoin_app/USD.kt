package com.example.bitcoin_app

import com.google.gson.annotations.SerializedName

data class USD(
    @SerializedName("price") val price: Double,
    @SerializedName("percent_change_24h") val percent_change_24h: Double
)