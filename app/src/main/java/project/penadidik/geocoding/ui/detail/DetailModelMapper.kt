package project.penadidik.geocoding.ui.detail

import project.penadidik.geocoding.base.BaseMapper
import project.penadidik.geocoding.base.BaseModel
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.model.Model
import javax.inject.Inject

class DetailModelMapper @Inject constructor() : BaseMapper<Detail, DetailModel> {
    override fun mapToPresentation(model: Detail): DetailModel = DetailModel(
        dt = model.dt,
        temp_in_main = model.main!!.temp.toString(),
        feels_like_in_main = model.main!!.feels_like,
        temp_min_in_main = model.main!!.temp_min,
        temp_max_in_main = model.main!!.temp_max,
        pressure_in_main = model.main!!.pressure,
        sea_level_in_main = model.main!!.sea_level,
        grnd_level_in_main = model.main!!.grnd_level,
        humidity_in_main = model.main!!.humidity,
        temp_kf_in_main = model.main!!.temp_kf,
        id_in_weather = model.weather!![0].id,
        main_in_weather = model.weather!![0].main,
        description_in_weather = model.weather!![0].description,
        icon_in_weather = model.weather!![0].icon,
        all_in_clouds = model.clouds!!.all,
        speed_in_wind = model.wind!!.speed.toString(),
        deg_in_wind = model.wind!!.deg,
        gust_in_wind = model.wind!!.gust.toString(),
        pop = model.pop,
        visibility = model.visibility,
        dt_txt = model.dt_txt
    )

    override fun mapToDomain(modelItem: DetailModel): Model {
        TODO("Not yet implemented")
    }
}