package medpod.blood.service

import medpod.blood.controllers.model.ChangePrescriptionCommand
import medpod.blood.model.Prescription
import medpod.blood.repositories.PrescriptionsRepository
import org.springframework.stereotype.Service

@Service
class PrescriptionsService(
    private val prescriptionsRepository: PrescriptionsRepository
) {

    fun new(changePrescriptionCommand: ChangePrescriptionCommand) {
        prescriptionsRepository.insert(changePrescriptionCommand.prescription)
    }

    fun change(changePrescriptionCommand: ChangePrescriptionCommand) {
        requireNotNull(changePrescriptionCommand.prescription.id)
        prescriptionsRepository.save(changePrescriptionCommand.prescription)
    }

    fun find(patientId: String): List<Prescription> {
        return prescriptionsRepository.findAllByPatientIdOrderByStartDateDesc(patientId)
    }

}
