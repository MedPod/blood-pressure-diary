package medpod.blood.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class BloodPressure(
    @Positive
    @NotNull
    val lower: Int?,
    @Positive
    @NotNull
    val upper: Int?,
    @Positive
    val pulse: Int?,
)