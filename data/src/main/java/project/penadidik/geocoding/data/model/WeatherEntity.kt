package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "weather")
data class WeatherEntity(
    @field: SerializedName("id") val id: Int,
    @field: SerializedName("main") val main: String,
    @field: SerializedName("description") val description: String,
    @field: SerializedName("icon") val icon: String
): ModelEntity()