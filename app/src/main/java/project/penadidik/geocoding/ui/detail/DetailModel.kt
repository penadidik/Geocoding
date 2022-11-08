package project.penadidik.geocoding.ui.detail

import project.penadidik.geocoding.base.BaseModel
import project.penadidik.geocoding.domain.model.Weather

data class DetailModel (
    val dt: Int,
    val temp_in_main: String,
    val feels_like_in_main: Double,
    val temp_min_in_main: Double,
    val temp_max_in_main: Double,
    val pressure_in_main: Int,
    val sea_level_in_main: Int,
    val grnd_level_in_main: Int,
    val humidity_in_main: Int,
    val temp_kf_in_main: Double,
    val id_in_weather: Int,
    val main_in_weather: String,
    val description_in_weather: String,
    val icon_in_weather: String,
    val all_in_clouds: Int,
    val speed_in_wind: String,
    val deg_in_wind: Int,
    val gust_in_wind: String,
    val pop: Double,
    val visibility: Int,
    val dt_txt: String
): BaseModel()