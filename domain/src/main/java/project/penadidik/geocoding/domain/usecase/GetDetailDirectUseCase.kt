package project.penadidik.geocoding.domain.usecase

import io.reactivex.Single
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import javax.inject.Inject

open class GetDetailDirectUseCase @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository
) : UseCase<GetDetailDirectUseCase.Params, Single<List<Detail>>>() {

    override fun createObservable(params: Params?): Single<List<Detail>> {
        val result: Single<List<Detail>> = if (params != null) {
            geoCodingRepository.getDetail(lat = params.lat, lon = params.lon)
        } else Single.error(Throwable(""))

        return result
    }

    data class Params(val lat: Double, val lon: Double)
}