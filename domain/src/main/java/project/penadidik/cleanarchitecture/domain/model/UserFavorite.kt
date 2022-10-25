package project.penadidik.cleanarchitecture.domain.model

data class UserFavorite(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String
) : Model()