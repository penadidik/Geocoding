package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "direct")
data class DirectEntity(
    @field: SerializedName("name") val name: String,
    @field: SerializedName("lat") val lat: Double,
    @field: SerializedName("lon") val lon: Double,
    @field: SerializedName("country") val country: String,
    @field: SerializedName("state") val state: String
): ModelEntity()