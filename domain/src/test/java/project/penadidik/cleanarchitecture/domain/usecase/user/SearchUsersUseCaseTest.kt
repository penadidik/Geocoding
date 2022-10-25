package project.penadidik.cleanarchitecture.domain.usecase.user

import project.penadidik.cleanarchitecture.domain.createListUser
import project.penadidik.cleanarchitecture.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.mockito.Mockito

class SearchUsersUseCaseTest {
    private lateinit var searchUserUseCase: SearchUserUseCase

    private val userRepository = Mockito.mock(UserRepository::class.java)

    @Before
    fun setup() {
        searchUserUseCase = SearchUserUseCase(userRepository)
    }

    @After
    fun clear() {
        searchUserUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        val params = SearchUserUseCase.Params(query = Mockito.anyString(), pageNumber = Mockito.anyInt(), sort = Mockito.anyString(), fromServer = ArgumentMatchers.anyBoolean())
        searchUserUseCase.createObservable(params)

        Mockito.verify(userRepository).searchUsers(params.query, params.pageNumber, params.sort, params.fromServer)
    }

    @Test
    fun createObservableNull() {
        val test = searchUserUseCase.createObservable(null).test()
        test.assertError { true }
    }

    @Test
    fun getUserComplete() {
        BDDMockito.given(userRepository.searchUsers(query = Mockito.anyString(), page = Mockito.anyInt(), sort = Mockito.anyString(), fromServer = ArgumentMatchers.anyBoolean()))
            .willReturn(Single.just(createListUser()))
        val test = searchUserUseCase.createObservable(SearchUserUseCase.Params(
            Mockito.anyString(),
            Mockito.anyInt(),
            Mockito.anyString(),
            ArgumentMatchers.anyBoolean()
        )).test()
        test.assertComplete()
    }
}