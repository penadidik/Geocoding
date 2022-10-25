package project.penadidik.cleanarchitecture.domain.exception

import project.penadidik.cleanarchitecture.domain.annotation.ExceptionType
import project.penadidik.cleanarchitecture.domain.model.Tag

class InlineException(
    override val code: Int,
    vararg val tags: Tag
) : project.penadidik.cleanarchitecture.domain.exception.CleanException(code, ExceptionType.INLINE, null)