package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.DetailResponse
import project.penadidik.geocoding.domain.model.Detail
import javax.inject.Inject

class DetailResponseMapper @Inject constructor() : ResponseMapper<Detail, DetailResponse> {
    override fun mapToDomain(response: DetailResponse): Detail {
        val mainResponseMapper = MainResponseMapper()
        val cloudsResponseMapper = CloudsResponseMapper()
        val weatherResponseMapper = WeatherResponseMapper()
        val windResponseMapper = WindResponseMapper()
        val detail = Detail()

        detail.apply {
            dt = response.dt
            main = mainResponseMapper.mapToDomain(response.main!!)
            weather = weatherResponseMapper.mapToDomainList(response.weather!!)
            clouds = cloudsResponseMapper.mapToDomain(response.clouds!!)
            wind = windResponseMapper.mapToDomain(response.wind!!)
            pop = response.pop
            visibility = response.visibility
            dt_txt = response.dt_txt
        }

        return detail
    }

    override fun mapToResponse(model: Detail): DetailResponse {
        val mainResponseMapper = MainResponseMapper()
        val cloudsResponseMapper = CloudsResponseMapper()
        val weatherResponseMapper = WeatherResponseMapper()
        val windResponseMapper = WindResponseMapper()

        return DetailResponse(
            dt = model.dt!!,
            main = mainResponseMapper.mapToResponse(model.main!!),
            weather = weatherResponseMapper.mapToResponses(model.weather!!),
            clouds = cloudsResponseMapper.mapToResponse(model.clouds!!),
            wind = windResponseMapper.mapToResponse(model.wind!!),
            pop = model.pop!!,
            visibility = model.visibility!!,
            dt_txt = model.dt_txt!!
        )
    }

    override fun mapToDomainList(responses: List<DetailResponse>): List<Detail> {
        val result =  ArrayList<Detail>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Detail>): List<DetailResponse> {
        val result =  ArrayList<DetailResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}