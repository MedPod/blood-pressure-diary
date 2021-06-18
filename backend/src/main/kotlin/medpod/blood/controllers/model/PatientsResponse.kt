package medpod.blood.controllers.model

import medpod.blood.model.Patient

data class PatientsResponse(
    val patients: List<Patient>
)
