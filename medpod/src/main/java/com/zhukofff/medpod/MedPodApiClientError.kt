package com.zhukofff.medpod

sealed class MedPodApiClientError
data class UnknownApiError(val code: Int): MedPodApiClientError()
object NetworkError: MedPodApiClientError()
object ItemNotFoundError: MedPodApiClientError()
