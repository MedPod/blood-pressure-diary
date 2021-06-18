package medpod.blood.controllers.model

import medpod.blood.model.Measure
import javax.validation.Valid

data class AddMeasurementCommand(
    @Valid
    val measure: Measure
)
