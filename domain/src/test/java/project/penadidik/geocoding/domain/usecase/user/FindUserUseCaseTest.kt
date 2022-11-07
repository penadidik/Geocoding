package project.penadidik.geocoding.domain.usecase.user

import project.penadidik.geocoding.domain.createUser
import project.penadidik.geocoding.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

class FindUserUseCaseTest {
    private lateinit var findUserUseCase: FindUserUseCase

    private val userRepository = mock(UserRepository::class.java)

    @Before
    fun setup() {
        findUserUseCase = FindUserUseCase(userRepository)
    }

    @After
    fun clear() {
        findUserUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        val params = FindUserUseCase.Params(userId = anyInt(), fromServer = anyBoolean())
        findUserUseCase.createObservable(params)

        verify(userRepository).getUser(params.userId, params.fromServer)
    }

    @Test
    fun createObservableNull() {
        val test = findUserUseCase.createObservable(null).test()
        test.assertError { true }
    }

    @Test
    fun getUserComplete() {
        given(userRepository.getUser(anyInt(), anyBoolean())).willReturn(Single.just(createUser()))
        val test = findUserUseCase.createObservable(FindUserUseCase.Params(anyInt(), anyBoolean())).test()
        test.assertComplete()
    }

    @Test
    fun getUserReturnData() {
        val params = FindUserUseCase.Params(userId = anyInt(), fromServer = anyBoolean())
        val user = createUser()

        `when`(userRepository.getUser(id = params.userId, fromServer = params.fromServer)).thenReturn(Single.just(user))
        val test = findUserUseCase.createObservable(params).test()
        test.assertValue(user)
    }
}