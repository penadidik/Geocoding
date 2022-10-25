package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType

class SnackBarException(
    override val code: Int,
    override val message: String
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.SNACK, message)