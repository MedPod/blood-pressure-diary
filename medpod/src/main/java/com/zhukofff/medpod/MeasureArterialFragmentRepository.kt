package com.zhukofff.medpod

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MeasureArterialFragmentRepository {

    val measurement = MutableLiveData<Measurement?>()
    var medpodApiClient = MedPodApiClient()

    fun sendMeasurementApiCall(measurement_: Measurement): MutableLiveData<Measurement?> {
        val call = medpodApiClient.medpodApi.addMeasurements(measurement_)

        call.enqueue(object: Callback<Measurement> {

            override fun onFailure(call: Call<Measurement>, t: Throwable) {
                Log.v("DEBUG: ", t.message.toString())
            }

            override fun onResponse(call: Call<Measurement>, response: Response<Measurement>) {

                Log.v("DEBUG: ", response.body().toString())
                if (response.body() != null) {
                    val data = response.body()
                    val lower = data!!.lower
                    val pulse = data!!.pulse
                    val upper = data!!.upper
                    val quiz = data!!.quiz
                    val timestamp = data!!.timestamp
                    measurement.value = Measurement(timestamp, lower, upper, pulse, quiz)
                } else {
                    measurement.value= null
                }
            }
        })
        return measurement
    }

}