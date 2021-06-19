package medpod.blood.exceptions

abstract class ApplicationException(message: String?, val code: String? = null) : RuntimeException(message)
