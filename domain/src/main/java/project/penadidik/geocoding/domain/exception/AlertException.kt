package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType

class AlertException(
    override val code: Int,
    override val message: String,
    val title: String? = null
) : project.penadidik.geocoding.domain.exception.CleanException(code, ExceptionType.ALERT, message)