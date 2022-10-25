package project.penadidik.cleanarchitecture.data.remote.response

import project.penadidik.cleanarchitecture.data.model.UserEntity
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<UserEntity>
)