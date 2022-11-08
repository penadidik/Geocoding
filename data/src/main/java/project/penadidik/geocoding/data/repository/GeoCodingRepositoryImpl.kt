package project.penadidik.geocoding.data.repository

import io.reactivex.Single
import project.penadidik.geocoding.data.local.db.AppDatabase
import project.penadidik.geocoding.data.model.mapper.DirectEntityMapper
import project.penadidik.geocoding.data.remote.api.GeoCodingApi
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.model.Direct
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoCodingRepositoryImpl @Inject constructor(
    private val geoCodingApi: GeoCodingApi,
    private val appDatabase: AppDatabase,
    private val mapper: DirectEntityMapper
) : GeoCodingRepository {
    override fun searchDirect(query: String, limit: Int): Single<List<Direct>> {
        return geoCodingApi.searchDirect(query, limit)
            .map { response ->
                response.map { mapper.mapToDomain(it) }
            }
            .doOnError { Throwable("Not found!") }
    }

    override fun getDetail(lat: Double, lon: Double): Single<List<Detail>> {
        TODO("Not yet implemented")
    }

    override fun setFavorite(lat: Double, long: Double): Single<Boolean> {
        TODO("Not yet implemented")
    }
}