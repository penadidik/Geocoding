package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "main")
data class MainEntity(
    @field: SerializedName("temp") val temp: Double,
    @field: SerializedName("feels_like") val feels_like: Double,
    @field: SerializedName("temp_min") val temp_min: Double,
    @field: SerializedName("temp_max") val temp_max: Double,
    @field: SerializedName("pressure") val pressure: Int,
    @field: SerializedName("sea_level") val sea_level: Int,
    @field: SerializedName("grnd_level") val grnd_level: Int,
    @field: SerializedName("humidity") val humidity: Int,
    @field: SerializedName("temp_kf") val temp_kf: Double
): ModelEntity()