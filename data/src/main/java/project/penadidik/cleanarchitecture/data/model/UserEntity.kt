package project.penadidik.cleanarchitecture.data.model

import androidx.room.Entity
import project.penadidik.cleanarchitecture.data.base.ModelEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user", primaryKeys = ["id"])
data class UserEntity(
    @field: SerializedName("id") val id: Int,
    @field: SerializedName("login") val login: String,
    @field: SerializedName("html_url") val htmlUrl: String,
    @field: SerializedName("avatar_url") val avatar: String,
    @field: SerializedName("favorite") var favorite: Boolean? = false
) : ModelEntity()
