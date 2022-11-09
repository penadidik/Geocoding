package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelResponse

data class WeatherResponse (
    @field: SerializedName("id") val id: Int,
    @field: SerializedName("main") val main: String,
    @field: SerializedName("description") val description: String,
    @field: SerializedName("icon") val icon: String
): ModelResponse()