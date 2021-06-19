package medpod.blood.controllers.model

import medpod.blood.model.Measure
import javax.validation.Valid

data class AddMeasurementCommand(
    val patientId: String,
    @Valid
    val measure: Measure
)
