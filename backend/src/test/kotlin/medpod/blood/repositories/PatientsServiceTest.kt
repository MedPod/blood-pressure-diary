package medpod.blood.repositories

import medpod.blood.AbstractSpringBootIT
import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.exceptions.NotFoundException
import medpod.blood.service.PatientsService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class PatientsServiceTest : AbstractSpringBootIT() {

    @Autowired
    lateinit var patientsService: PatientsService

    @Test
    internal fun `should register by snils`() {
        //given:
        val snils = "1"

        //when:
        val id = patientsService.register(RegisterPatientCommand(snils))

        //then:
        assertThat(patientsService.findById(id).snils)
            .isEqualTo(snils)
    }

    @Test
    internal fun `should throw exception when not found by id`() {
        //given:
        val snils = "1"
        val id = patientsService.register(RegisterPatientCommand(snils))

        //expect:
        assertThatThrownBy { (patientsService.findById("another-$id")) }
            .isInstanceOf(NotFoundException::class.java)
    }
}