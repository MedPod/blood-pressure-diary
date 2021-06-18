package medpod.blood.service

import medpod.blood.controllers.model.AddMeasurementCommand
import medpod.blood.controllers.model.DiaryMeasurementsResponse
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class DiaryService {

    fun diary(patient: String, from: ZonedDateTime?, to: ZonedDateTime?): DiaryMeasurementsResponse {
        TODO("Not yet implemented")
    }

    fun addMeasurement(addMeasurementCommand: AddMeasurementCommand): DiaryMeasurementsResponse {
        TODO("Not yet implemented")
    }

}
