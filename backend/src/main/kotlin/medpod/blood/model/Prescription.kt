package medpod.blood.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class Prescription(
    @Id
    val id: String? = null,
    val text: String,
    @Indexed
    val patientId: String,
    @DateTimeFormat(style = DATE_TIME_FORMAT)
    @Indexed
    val startDate: LocalDateTime = LocalDateTime.now()
)
