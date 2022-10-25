package project.penadidik.cleanarchitecture.data.repository

import project.penadidik.cleanarchitecture.data.local.db.AppDatabase
import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.data.model.UserFavoriteEntity
import project.penadidik.cleanarchitecture.data.model.mapper.UserEntityMapper
import project.penadidik.cleanarchitecture.data.model.mapper.UserFavoriteEntityMapper
import project.penadidik.cleanarchitecture.data.remote.api.UserApi
import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.domain.model.UserFavorite
import project.penadidik.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val appDatabase: AppDatabase,
    private val mapper: UserEntityMapper,
    private val mapperFavorite: UserFavoriteEntityMapper
) : UserRepository {

    override fun getUser(id: Int, fromServer: Boolean): Single<User> = when (fromServer) {
        false -> appDatabase.userDao().findById(id).map { mapper.mapToDomain(it) }
        true -> userApi.getUser(id.toString())
            .map { mapper.mapToDomain(it) }
            .onErrorResumeNext(getUser(id, false))
    }

    override fun searchUsers(query: String, page: Int?, sort: String, fromServer: Boolean): Single<List<User>> = when (fromServer) {
        false -> appDatabase.userDao().search(query, sort == "ASC").map { mapper.mapToDomainList(it) }
        true -> {
            userApi.searchUsers(query = query, page = page ?: 0, sort = sort)
                .map { response ->
                    response.items.map {
                        set(response.items)
                        mapper.mapToDomain(it)
                    }
                }
                .doOnError { Throwable("Not found!") }
        }
    }

    override fun setFavorite(idUser: Int, isEnabled: Boolean): Single<User> {
        val favorite = if (isEnabled) 1 else 0
        appDatabase.userDao().setFavorite(idUser, favorite)

        return appDatabase.userDao().findById(idUser).map {
            val userFavoriteEntity = UserFavoriteEntity(
                id = it.id,
                login = it.login,
                avatar = it.avatar,
                htmlUrl = it.htmlUrl
            )

            if (isEnabled) appDatabase.userFavoriteDao().insert(userFavoriteEntity)
            else appDatabase.userFavoriteDao().delete(userFavoriteEntity)
            mapper.mapToDomain(it)
        }
    }

    override fun searchUsersFavorite(): Single<List<UserFavorite>>  {
        return appDatabase.userFavoriteDao().getAll().map { mapperFavorite.mapToDomainList(it) }
    }

    override fun deleteFavorite(idUser: Int): Single<Boolean> {
        val favorite = appDatabase.userFavoriteDao().getById(idUser)
        appDatabase.userFavoriteDao().delete(favorite)
        return Single.just(true)
    }

    private fun set(userEntities: List<UserEntity>, page: Int = 1) {
        val userFavorites = appDatabase.userFavoriteDao().getAllFavorites()
        if (page == 1) appDatabase.userDao().nukeTable()
        userEntities.forEach {
           userFavorites.forEach { fav ->
               if (fav.id == it.id) {
                   it.favorite = true
               }
           }
        }

        appDatabase.userDao().insertAll(userEntities)
    }
}