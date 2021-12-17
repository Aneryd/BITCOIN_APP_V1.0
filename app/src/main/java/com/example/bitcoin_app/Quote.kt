package com.example.bitcoin_app

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD") val usd: USD
)