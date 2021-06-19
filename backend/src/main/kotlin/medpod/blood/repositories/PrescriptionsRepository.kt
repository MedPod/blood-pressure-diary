package medpod.blood.repositories

import medpod.blood.model.Prescription
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PrescriptionsRepository : MongoRepository<Prescription, String> {
    fun findAllByPatientIdOrderByStartDateDesc(patientId: String): List<Prescription>
}
