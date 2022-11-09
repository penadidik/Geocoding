package project.penadidik.geocoding.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import project.penadidik.geocoding.data.base.ModelEntity

@Entity(tableName = "detail")
class DetailEntity: ModelEntity() {
    @PrimaryKey(autoGenerate = true) 
    var id: Int = 0
    var dt: Int? = 0
    var temp_in_main: String? = null
    var feels_like_in_main: Double? = null
    var temp_min_in_main: Double? = null
    var temp_max_in_main: Double? = null
    var pressure_in_main: Int? = null
    var sea_level_in_main: Int? = null
    var grnd_level_in_main: Int? = null
    var humidity_in_main: Int? = null
    var temp_kf_in_main: Double? = null
    var id_in_weather: Int? = null
    var main_in_weather: String? = null
    var description_in_weather: String? = null
    var icon_in_weather: String? = null
    var all_in_clouds: Int? = null
    var speed_in_wind: String? = null
    var deg_in_wind: Int? = null
    var gust_in_wind: String? = null
    var pop: Double? = null
    var visibility: Int? = null
    var dt_txt: String? = null
    var lat: Double? = null
    var lon: Double? = null
}