package com.zhukofff.medpod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MeasureArterialViewModel: ViewModel() {

    val medPodApiClient = MedPodApiClient()
    var measurementLiveData: MutableLiveData<Measurement?>?= null

    fun sendMeasurements(systolic: String, diastolic: String) : LiveData<Measurement?>? {
        val quiz = Quiz("ok", "i wanna eat and drink coffee :)")
        val measurementToAdd = Measurement("20-06-2001", systolic.toInt(), diastolic.toInt(),60, quiz)
        measurementLiveData = MeasureArterialFragmentRepository.sendMeasurementApiCall(measurementToAdd)
        return measurementLiveData
    }
}