package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "clouds")
data class CloudsEntity(
    @field: SerializedName("all") val all: Int
): ModelEntity()