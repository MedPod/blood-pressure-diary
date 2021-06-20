package com.zhukofff.medpod

import com.google.gson.annotations.SerializedName
import java.util.*

data class Patient(
    @SerializedName("id") val id: String,
    @SerializedName("sex") val sex: String,
    @SerializedName("birth-date") val birthdate: Date,
    @SerializedName("name") val name: String,
    @SerializedName("last-name") val lastname: String,
    @SerializedName("middle-name") val middlename: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int
)
