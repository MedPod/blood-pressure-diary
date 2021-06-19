package medpod.blood.services

import medpod.blood.AbstractSpringBootIT
import medpod.blood.controllers.model.RegisterPatientCommand
import medpod.blood.exceptions.NotFoundException
import medpod.blood.repositories.DiaryRepository
import medpod.blood.service.PatientsService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

const val SOME_CORRECT_SNILS = "111-222-333"

internal class PatientsServiceTest : AbstractSpringBootIT() {

    @Autowired
    lateinit var patientsService: PatientsService

    @Autowired
    lateinit var diaryRepository: DiaryRepository

    @Test
    internal fun `should register by snils`() {
        //when:
        val id = register(SOME_CORRECT_SNILS)

        //then:
        assertThat(patientsService.findBySnils(SOME_CORRECT_SNILS).id)
            .isEqualTo(id)
    }

    @Test
    internal fun `should init diary`() {
        //when:
        val id = register(SOME_CORRECT_SNILS)

        //then:
        assertThat(diaryRepository.findById(id))
            .isNotNull
    }

    @Test
    internal fun `should find by snils`() {
        //when:
        val id = register(SOME_CORRECT_SNILS)

        //then:
        assertThat(patientsService.findById(id).snils)
            .isEqualTo(SOME_CORRECT_SNILS)
    }

    @Test
    internal fun `should throw exception when not found by id`() {
        //given:
        val id = register(SOME_CORRECT_SNILS)

        //expect:
        assertThatThrownBy { (patientsService.findById("another-$id")) }
            .isInstanceOf(NotFoundException::class.java)
    }

    private fun register(snils: String) = patientsService.register(RegisterPatientCommand(snils))
}