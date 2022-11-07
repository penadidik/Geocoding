package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType
import project.penadidik.geocoding.domain.model.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : project.penadidik.geocoding.domain.exception.CleanException(code, ExceptionType.REDIRECT, null)