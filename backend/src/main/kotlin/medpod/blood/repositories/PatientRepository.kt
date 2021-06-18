package medpod.blood.repositories

import medpod.blood.model.Patient
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository:MongoRepository<Patient, String>