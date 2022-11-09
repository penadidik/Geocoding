package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelResponse

data class CloudsResponse(
    @field: SerializedName("all") val all: Int
): ModelResponse()