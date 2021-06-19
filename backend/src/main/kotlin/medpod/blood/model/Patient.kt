package medpod.blood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Document
data class Patient(
    @field:Indexed(unique = true)
    val snils: String,
    @field:Id
    val id: String? = null,
    val optimalBloodPressure: BloodPressure? = null,
    val sex: Sex? = null,
    @field:DateTimeFormat(style = DATE_FORMAT)
    val birthDate: LocalDate? = null,
    val name: String? = null,
    val lastName: String? = null,
    val middleName: String? = null,
    val height: Int? = null,
    val weight: Int? = null
)
