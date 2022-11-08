package project.penadidik.geocoding.data.remote.api

import io.reactivex.Single
import project.penadidik.geocoding.data.Constants
import project.penadidik.geocoding.data.model.DirectEntity
import project.penadidik.geocoding.data.remote.response.BaseDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("geo/1.0/direct")
    fun searchDirect(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("appid") appid: String = Constants.Authentication.APPID
    ): Single<List<DirectEntity>>

    @GET("data/2.5/forecast")
    fun getDetail(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = Constants.Authentication.APPID
    ): Single<BaseDetailResponse>

}