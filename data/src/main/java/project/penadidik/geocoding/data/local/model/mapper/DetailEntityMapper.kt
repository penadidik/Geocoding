package project.penadidik.geocoding.data.local.model.mapper

import project.penadidik.geocoding.data.base.EntityMapper
import project.penadidik.geocoding.data.local.model.DetailEntity
import project.penadidik.geocoding.domain.model.Detail
import javax.inject.Inject

class DetailEntityMapper @Inject constructor() : EntityMapper<Detail, DetailEntity> {
    override fun mapToEntity(model: Detail): DetailEntity {
        val detailEntity = DetailEntity()
        detailEntity.dt = model.dt
        detailEntity.temp_in_main = model.main!!.temp.toString()
        detailEntity.feels_like_in_main = model.main!!.feels_like
        detailEntity.temp_min_in_main = model.main!!.temp_min
        detailEntity.temp_max_in_main = model.main!!.temp_max
        detailEntity.pressure_in_main = model.main!!.pressure
        detailEntity.sea_level_in_main = model.main!!.sea_level
        detailEntity.grnd_level_in_main = model.main!!.grnd_level
        detailEntity.humidity_in_main = model.main!!.humidity
        detailEntity.temp_kf_in_main = model.main!!.temp_kf
        detailEntity.id_in_weather = model.weather!![0].id
        detailEntity.main_in_weather = model.weather!![0].main
        detailEntity.description_in_weather = model.weather!![0].description
        detailEntity.icon_in_weather = model.weather!![0].icon
        detailEntity.all_in_clouds = model.clouds!!.all
        detailEntity.speed_in_wind = model.wind!!.speed.toString()
        detailEntity.deg_in_wind = model.wind!!.deg
        detailEntity.gust_in_wind = model.wind!!.gust.toString()
        detailEntity.pop = model.pop
        detailEntity.visibility = model.visibility
        detailEntity.dt_txt = model.dt_txt
        detailEntity.lat = model.lat
        detailEntity.lon = model.lon

        return detailEntity
    }

    override fun mapToDomain(modelItem: DetailEntity): Detail {
        TODO("Not yet implemented")
        val detailEntity = DetailEntity()
    }

    override fun mapToDomainList(entities: List<DetailEntity>): List<Detail> {
        TODO("Not yet implemented")
    }

    override fun mapToEntities(modelList: List<Detail>): List<DetailEntity> {
        val result =  ArrayList<DetailEntity>()
        modelList.forEach {
            result.add(mapToEntity(it))
        }
        return result
    }
}