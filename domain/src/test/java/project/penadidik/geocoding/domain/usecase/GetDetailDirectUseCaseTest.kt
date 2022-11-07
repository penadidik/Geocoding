package project.penadidik.geocoding.domain.usecase

import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import project.penadidik.geocoding.domain.GeoCodingTestModel
import project.penadidik.geocoding.domain.repository.GeoCodingRepository

class GetDetailDirectUseCaseTest {

    private lateinit var getDetailDirectUseCase: GetDetailDirectUseCase
    private val geoCodingRepository = Mockito.mock(GeoCodingRepository::class.java)

    @Before
    fun setup() {
        getDetailDirectUseCase = GetDetailDirectUseCase(geoCodingRepository)
    }

    @After
    fun clear() {
        getDetailDirectUseCase.onCleared()
    }

    @Test
    fun createObservable() {
        val params = GetDetailDirectUseCase.Params(lat = Mockito.anyDouble(), lon = Mockito.anyDouble())
        getDetailDirectUseCase.createObservable(params)

        Mockito.verify(geoCodingRepository).getDetail(params.lat, params.lon)
    }

    @Test
    fun createObservableNull() {
        val test = getDetailDirectUseCase.createObservable(null).test()
        test.assertError {true }
    }

    @Test
    fun getGeoCodingComplete() {
        BDDMockito.given(geoCodingRepository.getDetail(lat = Mockito.anyDouble(), lon = Mockito.anyDouble()))
            .willReturn(Single.just(GeoCodingTestModel.createDetail()))

        val test = getDetailDirectUseCase.createObservable(GetDetailDirectUseCase.Params(
            Mockito.anyDouble(),
            Mockito.anyDouble()
        )).test()
        test.assertComplete()
    }

    @Test
    fun getGeoCodingReturnData() {
        val params = GetDetailDirectUseCase.Params(lat = Mockito.anyDouble(), lon = Mockito.anyDouble())
        val geoCoding = GeoCodingTestModel.createDetail()

        Mockito.`when`(geoCodingRepository.getDetail(lat = params.lat, lon = params.lon))
            .thenReturn(
            Single.just(geoCoding))
        val test = getDetailDirectUseCase.createObservable(params).test()
        test.assertValue(geoCoding)
    }

}