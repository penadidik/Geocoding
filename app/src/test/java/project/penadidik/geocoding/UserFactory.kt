package project.penadidik.geocoding

import project.penadidik.geocoding.domain.model.User
import project.penadidik.geocoding.model.UserModel

fun createUserItem(): UserModel = UserModel(id = 1,
        login = "Didik",
        avatar = "ini adalah penadidik",
        htmlUrl = "Penadidik",
        isFavorite = false)

fun createUser(): User = User(id = 1,
        login = "Didik",
        avatar = "ini adalah penadidik",
        htmlUrl = "Penadidik",
        isFavorite = false)