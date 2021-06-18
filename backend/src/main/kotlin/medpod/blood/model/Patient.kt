package medpod.blood.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class Patient(
    val id: String,
    val optimalBloodPressure: BloodPressure?,
    val sex: Sex?,
    @DateTimeFormat(style = DATE_FORMAT)
    val birthDate: LocalDate?,
    val name: String?,
    val lastName: String?,
    val middleName: String?,
    val height: Int?,
    val weight: Int?
)
