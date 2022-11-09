package project.penadidik.geocoding.domain.usecase

import io.reactivex.Single
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.model.Direct
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import javax.inject.Inject

open class SetFavoriteDirectUseCase @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository
) : UseCase<SetFavoriteDirectUseCase.Params, Single<Boolean>>() {

    override fun createObservable(params: Params?): Single<Boolean> {
        val result: Single<Boolean> = if (params != null) {
            geoCodingRepository.setFavorite(direct = params.direct, detail = params.details)
        } else Single.error(Throwable(""))

        return result
    }

    data class Params(val direct: Direct, val details: List<Detail>)
}