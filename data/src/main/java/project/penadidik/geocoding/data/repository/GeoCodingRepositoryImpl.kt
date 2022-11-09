package project.penadidik.geocoding.data.repository

import android.os.AsyncTask
import io.reactivex.Single
import project.penadidik.geocoding.data.local.db.AppDatabase
import project.penadidik.geocoding.data.local.model.DetailEntity
import project.penadidik.geocoding.data.local.model.DirectEntity
import project.penadidik.geocoding.data.local.model.mapper.DetailEntityMapper
import project.penadidik.geocoding.data.local.model.mapper.DirectEntityMapper
import project.penadidik.geocoding.data.remote.api.GeoCodingApi
import project.penadidik.geocoding.data.remote.mapper.DetailResponseMapper
import project.penadidik.geocoding.data.remote.mapper.DirectResponseMapper
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.model.Direct
import project.penadidik.geocoding.domain.repository.GeoCodingRepository
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine

@Singleton
class GeoCodingRepositoryImpl @Inject constructor(
    private val geoCodingApi: GeoCodingApi,
    private val appDatabase: AppDatabase,
    private val directResponseMapper: DirectResponseMapper,
    private val detailResponseMapper: DetailResponseMapper,
    private val directEntityMapper: DirectEntityMapper,
    private val detailEntityMapper: DetailEntityMapper
) : GeoCodingRepository {
    override fun searchDirect(query: String, limit: Int): Single<List<Direct>> {
        return geoCodingApi.searchDirect(query, limit)
            .map { directResponseMapper.mapToDomainList(it) }
            .doOnError { Throwable("Not found!") }
    }

    override fun getDetail(lat: Double, lon: Double): Single<List<Detail>> {
        return geoCodingApi.getDetail(lat, lon)
            .map { detailResponseMapper.mapToDomainList(it.list) }
            .doOnError { Throwable("Not found!") }
    }

    override fun setFavorite(direct: Direct, detail: List<Detail>): Single<Boolean> {
        val directEntity = directEntityMapper.mapToEntity(direct)
        val detailEntities = detailEntityMapper.mapToEntities(detail)
        val dbDirect = appDatabase.directDao()
        val dbDetail = appDatabase.detailDao()

        if (dbDirect.getBy(direct.lat!!, direct.lon!!) != null) {
            dbDirect.update(directEntity)
        } else {
            dbDirect.insert(directEntity)
        }

        detailEntities.forEach {
            if (dbDetail.getBy(it.lat!!, it.lon!!, it.dt_txt!!) != null) {
                dbDetail.update(it)
            } else {
                dbDetail.insert(it)
            }
        }

        return Single.just(true)
    }
}