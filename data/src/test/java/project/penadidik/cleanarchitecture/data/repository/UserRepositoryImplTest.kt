package project.penadidik.cleanarchitecture.data.repository

import project.penadidik.cleanarchitecture.data.local.db.AppDatabase
import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.data.model.mapper.UserEntityMapper
import project.penadidik.cleanarchitecture.data.model.mapper.UserFavoriteEntityMapper
import project.penadidik.cleanarchitecture.data.remote.api.UserApi
import io.reactivex.Single
import io.reactivex.SingleObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class UserRepositoryImplTest {
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    private val userApiMock = mock(UserApi::class.java)
    private val appDatabaseMock = mock(AppDatabase::class.java)
    private val userEntityMapper = UserEntityMapper()
    private val userFavoriteEntityMapper = UserFavoriteEntityMapper()

    @Before
    fun setup() {
        userRepositoryImpl = UserRepositoryImpl(userApiMock, appDatabaseMock, userEntityMapper, userFavoriteEntityMapper)
    }

    @Test
    fun findUser() {
        `when`(userApiMock.getUser(anyString())).thenReturn(object: Single<UserEntity>() {
            override fun subscribeActual(observer: SingleObserver<in UserEntity>) {

            }

        })
    }
}