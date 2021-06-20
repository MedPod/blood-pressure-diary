package com.zhukofff.medpod

import com.google.gson.annotations.SerializedName
import java.util.*

data class Measurement(
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("lower") val lower: Int,
    @SerializedName("upper") val upper: Int,
    @SerializedName("pulse") val pulse: Int,
    @SerializedName("quiz") val quiz: Quiz,
    )
