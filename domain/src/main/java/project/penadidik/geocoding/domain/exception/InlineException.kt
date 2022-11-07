package project.penadidik.geocoding.domain.exception

import project.penadidik.geocoding.domain.annotation.ExceptionType
import project.penadidik.geocoding.domain.model.Tag

class InlineException(
    override val code: Int,
    vararg val tags: Tag
) : project.penadidik.geocoding.domain.exception.CleanException(code, ExceptionType.INLINE, null)