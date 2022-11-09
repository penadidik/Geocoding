package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelResponse

data class DirectResponse(
    @field: SerializedName("name") val name: String,
    @field: SerializedName("lat") val lat: Double,
    @field: SerializedName("lon") val lon: Double,
    @field: SerializedName("country") val country: String,
    @field: SerializedName("state") val state: String
): ModelResponse()