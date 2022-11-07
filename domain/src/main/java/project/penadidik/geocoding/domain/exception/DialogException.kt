package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType
import project.penadidik.geocoding.domain.model.Dialog

class DialogException(
    override val code: Int,
    val dialog: Dialog
) : project.penadidik.geocoding.domain.exception.CleanException(code, ExceptionType.DIALOG, null)