package com.zhukofff.medpod

object MedPodApiClientPlaygroud {
    @JvmStatic
    fun main() {
        val medPodApiClient = MedPodApiClient()

        val quiz = Quiz("ok", "i wanna eat and drink coffee :)")
        val measurementToAdd = Measurement("20-06-2001", 80, 120,60, quiz)
        val addedMeasurement = medPodApiClient.addMeasurement(measurementToAdd)
        println(addedMeasurement)
    }
}