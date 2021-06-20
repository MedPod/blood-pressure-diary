package com.zhukofff.medpod

import com.zhukofff.medpod.MedPodApiClientConfig.BASE_ENDPOINT
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class MedPodApiClient @JvmOverloads constructor(baseEndpoint: String = BASE_ENDPOINT) {

    val medpodApi: MedPodAPI

    init {
        val client = OkHttpClient().newBuilder().addInterceptor(DefaultHeadersInterceptor()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseEndpoint)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.medpodApi = retrofit.create(MedPodAPI::class.java)
    }

    fun addMeasurement(measurement: Measurement): Either<MedPodApiClientError, Measurement> = try {
        val response = medpodApi.addMeasurements("1", measurement).execute()
        inspectResponceForErrors(response)
    } catch (e: IOException) {
        Either.left(NetworkError)
    }

    private fun <T> inspectResponceForErrors(response: Response<T>): Either<MedPodApiClientError, T> = when  {
        response.code() == 404 -> Either.left(ItemNotFoundError)
        response.code() >= 400 -> Either.left(UnknownApiError(response.code()))
        response.body() == null -> Either.left(UnknownApiError(response.code()))
        else -> Either.right(response.body()!!)
    }
}