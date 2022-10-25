package project.penadidik.cleanarchitecture.model

import project.penadidik.cleanarchitecture.base.BaseModel

data class FavoriteModel(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String
) : BaseModel()