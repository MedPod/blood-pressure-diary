package medpod.blood.repositories

import medpod.blood.AbstractSpringBootIT
import medpod.blood.model.Patient
import medpod.blood.utils.Random
import medpod.blood.utils.nextObj
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class PatientRepositoryTest : AbstractSpringBootIT() {
    @Autowired
    lateinit var patientRepository: PatientRepository

    @Test
    internal fun should_find_all_by_snils() {
        //given:
        val patient1 = patient("111-222-333")
        val patient2 = patient("111-333-222")
        patient("222-111-333")

        //when:
        val patients = patientRepository.findAllMatchingSnilsStartsFrom("111")

        assertThat(patients)
            .extracting(Patient::id)
            .containsExactly(Tuple(patient1), Tuple(patient2))
    }

    private fun patient(snils: String) =
        patientRepository.save(
            Random.random.nextObj<Patient>().copy(snils = snils)
        ).id!!

}