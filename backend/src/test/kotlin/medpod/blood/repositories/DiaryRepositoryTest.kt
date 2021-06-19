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
    internal fun `should add measures`() {
        //given:
        val patientId = "1"
        diaryExists(patientId)
        val timestamp = LocalDateTime.of(2020, 12, 10, 1, 0)

        //when:
        val measure = measure(timestamp, patientId, 120)

        //then:
        val diary = diaryRepository.findMeasurements(
            patientId,
            timestamp.minusMonths(1),
            timestamp
        )
        assertThat(diary).hasSize(1)
            .first()
            .isEqualTo(measure)
    }

    @Test
    internal fun `should find measures`() {
        //given:
        val patientId = "1"
        diaryExists(patientId)
        val timestamp = LocalDateTime.of(2020, 12, 10, 1, 0)
        val measure = measure(timestamp, patientId, 120)
        measure(timestamp.minusMonths(2), patientId, 120)

        //when:
        val diary = diaryRepository.findMeasurements(
            patientId,
            timestamp.minusMonths(1),
            timestamp
        )

        //then:
        assertThat(diary).hasSize(1)
            .first()
            .isEqualTo(measure)
    }

    private fun measure(timestamp: LocalDateTime, patientId: String, upper: Int): Measure {
        val measure = Measure(
            timestamp,
            80,
            upper
        )
        diaryRepository.addMeasurement(
            patientId,
            measure
        )
        return measure
    }

    private fun diaryExists(patientId: String) {
        diaryRepository.save(Diary(patientId))
    }
}