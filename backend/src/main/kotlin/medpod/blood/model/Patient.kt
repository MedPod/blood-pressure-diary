package medpod.blood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class Patient(
    @Indexed(unique = true)
    val snils: String,
    @Id
    val id: String? = null,
    val optimalBloodPressure: BloodPressure? = null,
    val sex: Sex? = null,
    @DateTimeFormat(style = DATE_FORMAT)
    val birthDate: LocalDate? = null,
    val name: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val height: Int? = null,
    val weight: Int? = null
)
