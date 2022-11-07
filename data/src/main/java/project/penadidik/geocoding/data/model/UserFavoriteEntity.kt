package project.penadidik.geocoding.data.model

import androidx.room.Entity
import project.penadidik.geocoding.data.base.ModelEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_favorite", primaryKeys = ["id"])
data class UserFavoriteEntity(
    @field: SerializedName("id") val id: Int,
    @field: SerializedName("login") val login: String,
    @field: SerializedName("html_url") val htmlUrl: String,
    @field: SerializedName("avatar_url") val avatar: String
) : ModelEntity()