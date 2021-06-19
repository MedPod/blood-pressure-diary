package medpod.blood.controllers

import medpod.blood.controllers.model.PatientsResponse
import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.controllers.model.RegisterPatientResponse
import medpod.blood.controllers.model.UpdatePatientCommand
import medpod.blood.model.Patient
import medpod.blood.service.PatientsService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/patients")
class PatientController(
    private val patientsService: PatientsService
) {
    @GetMapping("/me")
    fun me(
        @RequestParam("snils") snils: String,
    ): Patient? = patientsService.findBySnils(snils)

    @GetMapping
    fun find(
        @RequestParam("query") query: String,
    ): PatientsResponse = PatientsResponse(patientsService.findMatching(query))

    @PostMapping("/update-info")
    fun update(
        @Valid @RequestBody updatePatient: UpdatePatientCommand
    ) = patientsService.update(updatePatient)

    @PostMapping("/register")
    fun register(
        @Valid @RequestBody registerPatientCommand: RegisterPatientCommand
    ): RegisterPatientResponse = RegisterPatientResponse(patientsService.register(registerPatientCommand))
}