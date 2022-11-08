package project.penadidik.geocoding.data.remote.response

import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.model.UserEntity

data class SearchUserResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<UserEntity>
)