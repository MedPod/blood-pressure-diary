package medpod.blood.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BusinessException(message: String? = null, code: String? = null) : ApplicationException(message, code)
