package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "wind")
data class WindEntity(
    @field: SerializedName("speed") val speed: Double,
    @field: SerializedName("deg") val deg: Int,
    @field: SerializedName("gust") val gust: Double
): ModelEntity()