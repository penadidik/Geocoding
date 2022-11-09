package project.penadidik.geocoding.ui.detail

import project.penadidik.geocoding.base.BaseMapper
import project.penadidik.geocoding.domain.model.*
import javax.inject.Inject

class DetailModelMapper @Inject constructor() : BaseMapper<Detail, DetailModel> {
    override fun mapToPresentation(model: Detail): DetailModel {
        val detailModel = DetailModel()

        detailModel.dt = model.dt!!
        detailModel.temp_in_main = model.main!!.temp.toString()
        detailModel.feels_like_in_main = model.main!!.feels_like
        detailModel.temp_min_in_main = model.main!!.temp_min
        detailModel.temp_max_in_main = model.main!!.temp_max
        detailModel.pressure_in_main = model.main!!.pressure
        detailModel.sea_level_in_main = model.main!!.sea_level
        detailModel.grnd_level_in_main = model.main!!.grnd_level
        detailModel.humidity_in_main = model.main!!.humidity
        detailModel.temp_kf_in_main = model.main!!.temp_kf
        detailModel.id_in_weather = model.weather!![0].id
        detailModel.main_in_weather = model.weather!![0].main
        detailModel.description_in_weather = model.weather!![0].description
        detailModel.icon_in_weather = model.weather!![0].icon
        detailModel.all_in_clouds = model.clouds!!.all
        detailModel.speed_in_wind = model.wind!!.speed.toString()
        detailModel.deg_in_wind = model.wind!!.deg
        detailModel.gust_in_wind = model.wind!!.gust.toString()
        detailModel.pop = model.pop
        detailModel.visibility = model.visibility
        detailModel.dt_txt = model.dt_txt

        return detailModel
    }

    override fun mapToDomain(modelItem: DetailModel): Detail {
        val main = Main()
        main.temp = modelItem.temp_in_main?.toDouble()
        main.temp_min = modelItem.temp_min_in_main
        main.temp_max = modelItem.temp_max_in_main
        main.feels_like = modelItem.feels_like_in_main
        main.pressure = modelItem.pressure_in_main
        main.sea_level = modelItem.sea_level_in_main
        main.grnd_level = modelItem.grnd_level_in_main
        main.humidity = modelItem.humidity_in_main
        main.temp_kf = modelItem.temp_kf_in_main

        val weather = Weather()
        weather.id = modelItem.id_in_weather
        weather.main = modelItem.temp_in_main
        weather.description = modelItem.description_in_weather
        weather.icon = modelItem.icon_in_weather
        val weatherList = ArrayList<Weather>()
        weatherList.add(weather)

        val clouds = Clouds()
        clouds.all = modelItem.all_in_clouds

        val wind = Wind()
        wind.speed = modelItem.speed_in_wind?.toDouble()
        wind.deg = modelItem.deg_in_wind
        wind.gust = modelItem.gust_in_wind?.toDouble()

        val detail = Detail()
        detail.dt = modelItem.dt
        detail.main = main
        detail.weather = weatherList
        detail.clouds = clouds
        detail.wind = wind
        detail.pop = modelItem.pop
        detail.visibility = modelItem.visibility
        detail.dt_txt = modelItem.dt_txt
        detail.lat = modelItem.lat
        detail.lon = modelItem.lon

        return detail
    }
}