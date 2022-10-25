package project.penadidik.cleanarchitecture

import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.model.UserModel

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