package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelResponse

data class WindResponse (
    @field: SerializedName("speed") val speed: Double,
    @field: SerializedName("deg") val deg: Int,
    @field: SerializedName("gust") val gust: Double
): ModelResponse()