package medpod.blood.repositories

import medpod.blood.AbstractSpringBootIT
import medpod.blood.model.Diary
import medpod.blood.model.Measure
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class DiaryRepositoryTest : AbstractSpringBootIT() {

    @Autowired
    lateinit var diaryRepository: DiaryRepository

    @Test
    internal fun `should find measures`() {
        //given:
        val timestamp = LocalDateTime.of(2020, 12, 10, 1, 0)
        val patientId = "1"
        val measure = Measure(
            timestamp,
            80,
            120
        )
        diaryRepository.save(Diary(patientId))
        diaryRepository.addMeasurement(
            patientId,
            measure
        )

        //when:
        val diary = diaryRepository.findMeasurements(
            patientId,
            timestamp.minusMonths(1),
            timestamp
        )!!

        //then:
        assertThat(diary.measures).hasSize(1)
            .first()
            .isEqualTo(measure)
    }
}