package project.penadidik.geocoding.data.remote.response

import project.penadidik.geocoding.data.model.UserEntity
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<UserEntity>
)