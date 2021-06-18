package medpod.blood.controllers.model

import medpod.blood.model.Patient

data class UpdatePatientCommand(
    val patient: Patient
)
