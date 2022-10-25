package project.penadidik.cleanarchitecture.data

import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.domain.model.User

fun createUserEntity(): UserEntity = UserEntity(id = 1,
        login = "Pena",
        avatar = "ini adalah penadidik",
        htmlUrl = "Penadidik",
        favorite = false)

fun createUser(): User = User(id = 1,
        login = "Pena",
        avatar = "ini adalah penadidik",
        htmlUrl = "Penadidik",
        isFavorite = false)