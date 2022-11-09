package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.domain.model.Detail

data class BaseDetailResponse (
    @SerializedName("cod") val cod: String,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<DetailResponse>
)