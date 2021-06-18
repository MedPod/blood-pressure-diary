package medpod.blood.service

import medpod.blood.repositories.PatientRepository
import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.controllers.model.UpdatePatientCommand
import medpod.blood.exceptions.NotFoundException
import medpod.blood.model.Patient
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientsService(
    private val patientRepository: PatientRepository
) {

    fun register(registerPatientCommand: RegisterPatientCommand): String =
        patientRepository.save(Patient(registerPatientCommand.snils)).id!!

    fun findBySnils(snils: String): Patient {
        TODO("Not yet implemented")
    }

    fun findById(id: String): Patient =
        patientRepository.findByIdOrNull(id)
            ?: throw NotFoundException("Пациент не найден")

    fun update(updatePatient: UpdatePatientCommand) {
        TODO("Not yet implemented")
    }

    fun findMathing(query: String): List<Patient> {
        TODO("Not yet implemented")
    }

}
