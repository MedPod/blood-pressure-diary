package medpod.blood

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

internal class ApplicationKtTest : AbstractSpringBootIT() {

    @Autowired
    lateinit var context: ApplicationContext

    @Test
    internal fun `should start context`() {
        assertThat(context).isNotNull
    }
}
