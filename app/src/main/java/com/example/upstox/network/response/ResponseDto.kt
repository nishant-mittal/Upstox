package com.example.upstox.network.response

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @field:SerializedName("data")
    val data: UserHoldingResponseDto? = null,
)

data class UserHoldingResponseDto(
    @field:SerializedName("userHolding")
    val userHolding: List<UserHoldingDto?>? = null,
)

