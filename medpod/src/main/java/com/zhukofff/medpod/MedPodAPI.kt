package com.zhukofff.medpod

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.text.DateFormat
import java.util.*

interface MedPodAPI {

    @GET("/patients/{snils}")
    suspend fun patients(
        @Path("snils") snils: String,
        @Query("id") id: Int,
        @Query("sex") sex: String,
        @Query("birth-date") birthdate: Date,
        @Query("name") name: String,
        @Query("last-name") lastname: String,
        @Query("middle-name") middlename: String,
        @Query("height") height: Int,
        @Query("weight") wieght: Int
        ) : Response<List<Patient>>

    @GET("/patients/{snils}/optimal-blood-pressure")
    suspend fun optimalBloodPressure(
        @Path("snils") snils: String,
        @Query("lower") lowerPressure: Int,
        @Query("upper") upperPressure: Int
    ) : Response<List<OptimalBloodPressure>>

    @POST("/patients/register/snils")
    fun addSnils(@Body snils: String): Call<String>

    @POST("/diary/measurements/add")
    fun addMeasurements(@Body patientId: String, @Body measurement: Measurement) : Call<Measurement>


}