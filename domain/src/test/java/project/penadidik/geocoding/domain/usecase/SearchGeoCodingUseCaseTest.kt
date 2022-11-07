package project.penadidik.geocoding.domain.usecase

import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import project.penadidik.geocoding.domain.GeoCodingTestModel
import project.penadidik.geocoding.domain.repository.GeoCodingRepository

class SearchGeoCodingUseCaseTest {

    private lateinit var searchDirectUseCase: SearchDirectUseCase
    private val geoCodingRepository = Mockito.mock(GeoCodingRepository::class.java)

    @Before
    fun setup() {
        searchDirectUseCase = SearchDirectUseCase(geoCodingRepository)
    }

    @After
    fun clear() {
        searchDirectUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        val params = SearchDirectUseCase.Params(query = Mockito.anyString(), limit = Mockito.anyInt())
        searchDirectUseCase.createObservable(params)

        Mockito.verify(geoCodingRepository).searchDirect(params.query, params.limit)
    }

    @Test
    fun createObservableNull() {
        val test = searchDirectUseCase.createObservable(null).test()
        test.assertError {true }
    }

    @Test
    fun getGeoCodingComplete() {
        BDDMockito.given(geoCodingRepository.searchDirect(query = Mockito.anyString(), limit = Mockito.anyInt()))
            .willReturn(Single.just(GeoCodingTestModel.createList()))

        val test = searchDirectUseCase.createObservable(SearchDirectUseCase.Params(
            Mockito.anyString(),
            Mockito.anyInt()
        )).test()
        test.assertComplete()
    }

}