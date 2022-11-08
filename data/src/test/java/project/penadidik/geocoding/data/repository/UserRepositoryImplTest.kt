package project.penadidik.geocoding.data.repository

import io.reactivex.Single
import io.reactivex.SingleObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import project.penadidik.geocoding.data.local.db.AppDatabase

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