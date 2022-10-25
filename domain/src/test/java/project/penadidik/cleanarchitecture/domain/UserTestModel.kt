package project.penadidik.cleanarchitecture.domain

import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.domain.model.UserFavorite

fun createUser() = User(id = 1,
        login = "Pena",
        avatar = "ini adalah penadidik",
        htmlUrl = "Penadidik",
        isFavorite = false)

fun createListUser() : List<User> {
        val users = ArrayList<User>()

        users.add(User(id = 1,
                login = "Didik Maryono",
                avatar = "ini adalah penadidik",
                htmlUrl = "Penadidik",
                isFavorite = false))

        users.add(User(id = 2,
                login = "Didik",
                avatar = "ini adalah penadidik",
                htmlUrl = "Penadidik",
                isFavorite = false))

        return users
}

fun createListUserFavorite() : List<UserFavorite> {
        val users = ArrayList<UserFavorite>()

        users.add(UserFavorite(id = 1,
                login = "Didik Maryono",
                avatar = "ini adalah penadidik",
                htmlUrl = "Penadidik"))

        users.add(UserFavorite(id = 2,
                login = "Didik",
                avatar = "ini adalah penadidik",
                htmlUrl = "Penadidik"))

        return users
}