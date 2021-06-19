package medpod.blood.controllers.model

import medpod.blood.model.Prescription

data class ChangePrescriptionCommand(
    val prescription: Prescription
)
