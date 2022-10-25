package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType
import project.penadidik.cleanarchitecture.domain.model.Dialog

class DialogException(
    override val code: Int,
    val dialog: Dialog
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.DIALOG, null)