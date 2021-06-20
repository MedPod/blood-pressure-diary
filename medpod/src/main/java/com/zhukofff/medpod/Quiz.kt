package com.zhukofff.medpod

import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("health") val health: String,
    @SerializedName("comment") val comment: String,
)
