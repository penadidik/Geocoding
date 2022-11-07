package project.penadidik.geocoding.domain.model

import project.penadidik.geocoding.domain.annotation.TagName

data class Tag(@TagName val name: String, val message: String?)