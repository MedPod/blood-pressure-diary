package medpod.blood.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class Measure(
    @DateTimeFormat(
        style = DATE_TIME_FORMAT
    )
    @Indexed
    val timestamp: LocalDateTime,
    @Positive
    @NotNull
    val lower: Int,
    @Positive
    @NotNull
    val upper: Int,
    @Positive
    val pulse: Int? = null,
    val quiz: Quiz = Quiz()
)
