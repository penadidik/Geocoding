package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType

class ToastException(
    override val code: Int,
    override val message: String
) : project.penadidik.geocoding.domain.exception.CleanException(code, ExceptionType.TOAST, message)