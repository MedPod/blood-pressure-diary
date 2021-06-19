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
        @DateTimeFormat(style = DATE_FORMAT) from: LocalDateTime = LocalDateTime.now().minusMonths(1),
        @DateTimeFormat(style = DATE_FORMAT) to: LocalDateTime = LocalDateTime.now(),
    ): DiaryMeasurementsResponse = DiaryMeasurementsResponse(diaryService.diary(patientId, from, to))

    @GetMapping("/measurements/add")
    fun addMeasurement(
        @Valid @RequestBody addMeasurementCommand: AddMeasurementCommand
    ) {
        diaryService.addMeasurement(addMeasurementCommand)
    }
}