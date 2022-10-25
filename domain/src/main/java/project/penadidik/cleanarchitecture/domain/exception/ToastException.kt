package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType

class ToastException(
    override val code: Int,
    override val message: String
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.TOAST, message)