package project.penadidik.geocoding.domain.usecase

import io.reactivex.Single
import project.penadidik.geocoding.domain.model.Direct
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import javax.inject.Inject

open class SearchDirectUseCase @Inject constructor(
    private val geoCodingRepository: GeoCodingRepository
) : UseCase<SearchDirectUseCase.Params, Single<List<Direct>>>() {

    override fun createObservable(params: Params?): Single<List<Direct>> {
        val result: Single<List<Direct>> = if (params != null) {
            geoCodingRepository.searchDirect(query = params.query, limit = params.limit)
        } else Single.error(Throwable(""))

        return result
    }

    data class Params(val query: String, val limit: Int)

}