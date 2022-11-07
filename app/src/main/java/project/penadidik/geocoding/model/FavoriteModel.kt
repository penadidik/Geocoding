package project.penadidik.geocoding.model

import project.penadidik.geocoding.base.BaseModel

data class FavoriteModel(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String
) : BaseModel()