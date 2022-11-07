package project.penadidik.geocoding.model

import project.penadidik.geocoding.base.BaseModel

data class UserModel(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String,
    var isFavorite: Boolean? = false
) : BaseModel()