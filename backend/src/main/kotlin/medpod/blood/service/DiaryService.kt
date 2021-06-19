package medpod.blood.service

import medpod.blood.controllers.model.AddMeasurementCommand
import medpod.blood.exceptions.NotFoundException
import medpod.blood.model.Diary
import medpod.blood.model.Measure
import medpod.blood.repositories.DiaryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DiaryService(
    private val diaryRepository: DiaryRepository
) {

    fun initDiary(patientId: String){
        diaryRepository.insert(Diary(patientId))
    }

    fun diary(patientId: String, from: LocalDateTime, to: LocalDateTime): List<Measure> {
        return diaryRepository.findMeasurements(patientId, from, to)
            ?.measures
            ?:throw NotFoundException("Дневник не найден")
    }

    fun addMeasurement(addMeasurementCommand: AddMeasurementCommand) {
        diaryRepository.addMeasurement(addMeasurementCommand.patientId, addMeasurementCommand.measure)
    }

}
