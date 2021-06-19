package medpod.blood.controllers

import medpod.blood.controllers.model.ChangePrescriptionCommand
import medpod.blood.model.Prescription
import medpod.blood.service.PrescriptionsService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/prescriptions")
class PrescriptionsController(
    private val prescriptionsService: PrescriptionsService
) {
    @GetMapping
    fun lastPrescription(
        @RequestParam("patientId") patientId: String,
    ): List<Prescription> = prescriptionsService.find(patientId)

    @PostMapping("/change")
    fun change(
        @Valid @RequestBody prescriptionCommand: ChangePrescriptionCommand
    ) {
        prescriptionsService.change(prescriptionCommand)
    }

    @PostMapping("/new")
    fun new(
        @Valid @RequestBody prescriptionCommand: ChangePrescriptionCommand
    ) {
        prescriptionsService.new(prescriptionCommand)
    }
}