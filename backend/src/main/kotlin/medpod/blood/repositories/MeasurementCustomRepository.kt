package medpod.blood.repositories

import medpod.blood.model.Diary
import medpod.blood.model.Measure
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.*
import java.time.LocalDateTime


interface MeasurementCustomRepository {
    fun addMeasurement(
        patientId: String,
        measure: Measure
    )

    fun findMeasurements(patientId: String, from: LocalDateTime, to: LocalDateTime): List<Measure>
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

    override fun findMeasurements(patientId: String, from: LocalDateTime, to: LocalDateTime): List<Measure> {
        val aggregation: Aggregation = newAggregation(
            match(
                Criteria.where(Diary::patientId.name).isEqualTo(patientId)
            ),
            unwind("measures"),
            project("measures"),
            replaceRoot("measures"),
            match(
                Criteria.where("timestamp").gte(from).lte(to)
            )
        )
        return mongoTemplate.aggregate(
            aggregation,
            Diary::class.java,
            Measure::class.java
        ).mappedResults
    }

}