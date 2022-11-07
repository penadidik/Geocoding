package project.penadidik.geocoding.domain.repository

import io.reactivex.Single
import project.penadidik.geocoding.domain.model.DirectGeoCoding
import project.penadidik.geocoding.domain.model.OpenDetail

interface GeoCodingRepository: Repository {

    fun searchDirect(query: String, limit: Int): Single<List<DirectGeoCoding>>

    fun getDetail(lat: Double, lon: Double): Single<OpenDetail>

    fun setFavorite(lat: Double, long: Double): Single<Boolean>

}