package project.penadidik.cleanarchitecture.model

import project.penadidik.cleanarchitecture.base.BaseModel

data class UserModel(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String,
    var isFavorite: Boolean? = false
) : BaseModel()