package medpod.blood.controllers

import medpod.blood.controllers.model.AddMeasurementCommand
import medpod.blood.model.DATE_FORMAT
import medpod.blood.controllers.model.DiaryMeasurementsResponse
import medpod.blood.service.DiaryService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/diary")
class DiaryController(
    private val diaryService: DiaryService
) {
    @GetMapping("/{patient-id}")
    fun diary(
        @PathVariable("patient-id") patientId: String,
        @DateTimeFormat(style = DATE_FORMAT) from: LocalDateTime?,
        @DateTimeFormat(style = DATE_FORMAT) to: LocalDateTime?,
    ): DiaryMeasurementsResponse {
        val now = LocalDateTime.now()
        return DiaryMeasurementsResponse(
            diaryService.diary(
                patientId,
                from ?: now.minusMonths(1),
                to ?: now
            )
        )
    }

    @PostMapping("/measurements/add")
    fun addMeasurement(
        @Valid @RequestBody addMeasurementCommand: AddMeasurementCommand
    ) {
        diaryService.addMeasurement(addMeasurementCommand)
    }
}