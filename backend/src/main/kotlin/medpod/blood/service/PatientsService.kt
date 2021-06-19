package medpod.blood.service

import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.controllers.model.UpdatePatientCommand
import medpod.blood.exceptions.BusinessException
import medpod.blood.exceptions.NotFoundException
import medpod.blood.exceptions.PatientNotFoundException
import medpod.blood.model.Patient
import medpod.blood.repositories.PatientRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientsService(
    private val patientRepository: PatientRepository,
    private val diaryService: DiaryService
) {

    fun register(registerPatientCommand: RegisterPatientCommand): String {
        if (patientRepository.findBySnils(registerPatientCommand.snils) != null){
            throw BusinessException("Пользователь с таким СНИЛС уже зарегистрирован", "user_exists")
        }
        val patientId = patientRepository.insert(Patient(registerPatientCommand.snils)).id!!
        diaryService.initDiary(patientId)
        return patientId
    }

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
        return patientRepository.findAllMatchingSnilsStartsFrom(query)
    }

}
