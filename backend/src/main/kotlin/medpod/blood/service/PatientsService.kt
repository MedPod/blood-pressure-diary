package medpod.blood.service

import medpod.blood.controllers.model.PatientsResponse
import medpod.blood.controllers.model.UpdatePatientCommand
import medpod.blood.model.Patient
import org.springframework.stereotype.Service

@Service
class PatientsService {

    fun findBySnils(snils: String): Patient {
        TODO("Not yet implemented")
    }

    fun update(updatePatient: UpdatePatientCommand) {
        TODO("Not yet implemented")
    }

    fun findMathing(query: String): List<Patient> {
        TODO("Not yet implemented")
    }

}
