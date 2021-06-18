package medpod.blood.repositories

import medpod.blood.model.Patient
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository : MongoRepository<Patient, String> {

    fun findBySnils(snils: String): Patient?

    @Query("{'snils': {\$regex : '^?0.*', \$options: 'i'}}")
    fun findAllMatchingSnilsStartsFrom(snilsRegex: String): List<Patient>

}