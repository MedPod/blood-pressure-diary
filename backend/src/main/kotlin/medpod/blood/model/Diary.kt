package medpod.blood.model

import org.springframework.data.annotation.Id

data class Diary(
    @Id
    val patientId: String,
    val measures: List<Measure> = listOf()
)
