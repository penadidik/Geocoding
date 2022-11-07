package project.penadidik.geocoding.domain.model

data class User(
    val id: Int,
    val login: String,
    val avatar: String,
    val htmlUrl: String,
    val isFavorite: Boolean? = false
) : Model()
