package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelResponse

data class DetailResponse (
    @field: SerializedName("dt") val dt: Int,
    @field: SerializedName("main") val main: MainResponse? = null,
    @field: SerializedName("weather") val weather: List<WeatherResponse>? = arrayListOf(),
    @field: SerializedName("clouds") val clouds: CloudsResponse? = null,
    @field: SerializedName("wind") val wind: WindResponse? = null,
    @field: SerializedName("pop") val pop: Double,
    @field: SerializedName("visibility") val visibility: Int,
    @field: SerializedName("dt_txt") val dt_txt: String
): ModelResponse()