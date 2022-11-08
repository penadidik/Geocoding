package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.model.DetailEntity

data class BaseDetailResponse (
    @SerializedName("cod") val total: String,
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("list") val list: List<DetailEntity>
)