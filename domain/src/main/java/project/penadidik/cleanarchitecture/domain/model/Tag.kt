package project.penadidik.cleanarchitecture.domain.model

import project.penadidik.cleanarchitecture.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)