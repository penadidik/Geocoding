package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.WeatherResponse
import project.penadidik.geocoding.domain.model.Weather
import javax.inject.Inject

class WeatherResponseMapper @Inject constructor() : ResponseMapper<Weather, WeatherResponse> {
    override fun mapToDomain(response: WeatherResponse): Weather {
        val weather = Weather()
        weather.apply {
            id = response.id
            main = response.main
            description = response.description
            icon = response.icon
        }

        return weather
    }

    override fun mapToResponse(model: Weather): WeatherResponse = WeatherResponse(
        id = model.id!!,
        main = model.main!!,
        description = model.description!!,
        icon = model.icon!!
    )

    override fun mapToDomainList(responses: List<WeatherResponse>): List<Weather> {
        val result =  ArrayList<Weather>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Weather>): List<WeatherResponse> {
        val result =  ArrayList<WeatherResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}