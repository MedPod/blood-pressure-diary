package com.zhukofff.medpod

import com.google.gson.annotations.SerializedName

data class OptimalBloodPressure(
    @SerializedName("lower") val lowerPressure: Int,
    @SerializedName("upper") val upperPressure: Int
)
