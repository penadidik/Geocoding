package project.penadidik.geocoding.data

import project.penadidik.geocoding.domain.model.User

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