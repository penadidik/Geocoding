package project.penadidik.geocoding.domain.repository

import io.reactivex.Single
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.model.Direct

interface GeoCodingRepository: Repository {

    fun searchDirect(query: String, limit: Int): Single<List<Direct>>

    fun getDetail(lat: Double, lon: Double): Single<List<Detail>>

    fun setFavorite(direct: Direct, detail: List<Detail>): Single<Boolean>

}