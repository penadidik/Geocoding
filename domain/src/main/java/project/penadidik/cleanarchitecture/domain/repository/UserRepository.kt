package project.penadidik.cleanarchitecture.domain.repository

import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.domain.model.UserFavorite
import io.reactivex.Single

interface UserRepository : Repository {
    fun getUser(id: Int, fromServer: Boolean): Single<User>

    fun searchUsers(query: String, page: Int? = 1, sort: String, fromServer: Boolean): Single<List<User>>

    fun setFavorite(idUser: Int, isEnabled: Boolean): Single<User>

    fun searchUsersFavorite(): Single<List<UserFavorite>>

    fun deleteFavorite(idUser: Int): Single<Boolean>

}