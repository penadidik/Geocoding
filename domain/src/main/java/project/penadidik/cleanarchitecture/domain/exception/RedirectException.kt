package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType
import project.penadidik.cleanarchitecture.domain.model.Redirect

class RedirectException(
    override val code: Int,
    val redirect: Redirect
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.REDIRECT, null)