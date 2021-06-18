package medpod.blood.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
open class NotFoundException(message: String? = null) : ApplicationException(message)
