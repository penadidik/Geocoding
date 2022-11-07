package project.penadidik.geocoding.domain.usecase.user

import project.penadidik.geocoding.domain.createListUserFavorite
import project.penadidik.geocoding.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito

class SearchUsersFavoriteUseCaseTest {
    private lateinit var searchUsersFavoriteUseCase: GetAllUserFavoriteUseCase

    private val userRepository = Mockito.mock(UserRepository::class.java)

    @Before
    fun setup() {
        searchUsersFavoriteUseCase = GetAllUserFavoriteUseCase(userRepository)
    }

    @After
    fun clear() {
        searchUsersFavoriteUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        val params = GetAllUserFavoriteUseCase.Params()
        searchUsersFavoriteUseCase.createObservable(params)

        Mockito.verify(userRepository).searchUsersFavorite()
    }

    @Test
    fun getUserComplete() {
        BDDMockito.given(userRepository.searchUsersFavorite())
            .willReturn(Single.just(createListUserFavorite()))
        val test = searchUsersFavoriteUseCase.createObservable(GetAllUserFavoriteUseCase.Params()).test()
        test.assertComplete()
    }
}