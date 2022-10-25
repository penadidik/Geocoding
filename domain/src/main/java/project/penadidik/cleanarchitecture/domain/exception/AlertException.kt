package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType

class AlertException(
    override val code: Int,
    override val message: String,
    val title: String? = null
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.ALERT, message)