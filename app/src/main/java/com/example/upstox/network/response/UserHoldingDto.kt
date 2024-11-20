package com.example.upstox.network.response

import com.google.gson.annotations.SerializedName

data class UserHoldingDto(
    @field:SerializedName("symbol")
    val symbol: String? = null,

    @field:SerializedName("quantity")
    val quantity: Int? = null,

    @field:SerializedName("ltp")
    val ltp: Double? = null,

    @field:SerializedName("avgPrice")
    val avgPrice: Double? = null,

    @field:SerializedName("close")
    val close: Double? = null,
)
