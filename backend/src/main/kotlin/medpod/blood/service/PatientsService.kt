package medpod.blood.service

import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.controllers.model.UpdatePatientCommand
import medpod.blood.exceptions.NotFoundException
import medpod.blood.exceptions.PatientNotFoundException
import medpod.blood.model.Patient
import medpod.blood.repositories.PatientRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientsService(
    private val patientRepository: PatientRepository
) {

    fun register(registerPatientCommand: RegisterPatientCommand): String =
        patientRepository.save(Patient(registerPatientCommand.snils)).id!!

    fun findBySnils(snils: String): Patient =
        patientRepository.findBySnils(snils)
            ?: throw PatientNotFoundException()

    fun findById(id: String): Patient =
        patientRepository.findByIdOrNull(id)
            ?: throw PatientNotFoundException()

    fun update(updatePatient: UpdatePatientCommand) {
        patientRepository.findById(updatePatient.patient.id!!)
            .let {
                patientRepository.save(updatePatient.patient)
            }
    }

    fun findMatching(query: String): List<Patient> {
        TODO("Not yet implemented")
    }

}
