package medpod.blood

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit.jupiter.SpringExtension


@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SpringExtension::class)
abstract class AbstractSpringBootIT {

    @LocalServerPort
    private var port = -1

    @BeforeEach
    internal fun setUpRestAssured() {
        RestAssured.port = port
    }

}
