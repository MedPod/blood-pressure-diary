package medpod.blood.controllers.model

import medpod.blood.model.Measure

data class DiaryMeasurementsResponse(
    val measurements: List<Measure>
)