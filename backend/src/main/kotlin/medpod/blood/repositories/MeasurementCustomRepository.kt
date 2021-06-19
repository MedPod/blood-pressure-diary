package medpod.blood.repositories

import medpod.blood.model.Diary
import medpod.blood.model.Measure
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.*
import java.time.LocalDateTime


interface MeasurementCustomRepository {
    fun addMeasurement(
        patientId: String,
        measure: Measure
    )

    fun findMeasurements(patientId: String, from: LocalDateTime, to: LocalDateTime): Diary?
}

class MeasurementCustomRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : MeasurementCustomRepository {

    override fun addMeasurement(patientId: String, measure: Measure) {
        mongoTemplate.findAndModify(
            Query.query(where(Diary::patientId).isEqualTo(patientId)),
            Update().addToSet(Diary::measures.name, measure),
            Diary::class.java
        )
    }

    override fun findMeasurements(patientId: String, from: LocalDateTime, to: LocalDateTime): Diary? {
        val criteria = Criteria
            .where(Diary::patientId.name).isEqualTo(patientId)
            .and(Diary::measures.name)
            .elemMatch(Criteria(Measure::timestamp.name).gte(from).lte(to))
        return mongoTemplate.find(Query.query(criteria), Diary::class.java)
            .firstOrNull()

    }

}