package project.penadidik.geocoding.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "detail")
data class DetailEntity(
    @field: SerializedName("dt") val dt: Int,
    @field: SerializedName("main") val main: MainEntity? = null,
    @field: SerializedName("weather") val weather: List<WeatherEntity>? = arrayListOf(),
    @field: SerializedName("clouds") val clouds: CloudsEntity? = null,
    @field: SerializedName("wind") val wind: WindEntity? = null,
    @field: SerializedName("pop") val pop: Int,
    @field: SerializedName("visibility") val visibility: Int,
    @field: SerializedName("dt_txt") val dt_txt: String
): ModelEntity()