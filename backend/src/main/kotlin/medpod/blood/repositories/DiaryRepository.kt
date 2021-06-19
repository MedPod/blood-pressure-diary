package medpod.blood.repositories

import medpod.blood.model.Diary
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DiaryRepository : MongoRepository<Diary, String>, MeasurementCustomRepository {

}