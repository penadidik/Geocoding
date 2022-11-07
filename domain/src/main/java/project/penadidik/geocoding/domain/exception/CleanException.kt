package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType

open class CleanException(
    open val code: Int,
    @ExceptionType val exceptionType: Int,
    override val message: String?
) : Throwable(message)