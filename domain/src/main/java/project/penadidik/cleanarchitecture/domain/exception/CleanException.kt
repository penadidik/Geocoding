package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType

open class CleanException(
    open val code: Int,
    @ExceptionType val exceptionType: Int,
    override val message: String?
) : Throwable(message)