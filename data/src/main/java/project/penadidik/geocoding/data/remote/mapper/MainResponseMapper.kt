package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.MainResponse
import project.penadidik.geocoding.domain.model.Main
import javax.inject.Inject

class MainResponseMapper @Inject constructor() : ResponseMapper<Main, MainResponse> {
    override fun mapToDomain(response: MainResponse): Main {
        val main = Main()
        main.apply {
            temp = response.temp
            feels_like = response.feels_like
            temp_min = response.temp_min
            temp_max = response.temp_max
            pressure = response.pressure
            sea_level = response.sea_level
            grnd_level = response.grnd_level
            humidity = response.humidity
            temp_kf = response.temp_kf
        }

        return main
    }

    override fun mapToResponse(model: Main): MainResponse = MainResponse(
        temp = model.temp!!,
        feels_like = model.feels_like!!,
        temp_min = model.temp_min!!,
        temp_max = model.temp_max!!,
        pressure = model.pressure!!,
        sea_level = model.sea_level!!,
        grnd_level = model.grnd_level!!,
        humidity = model.humidity!!,
        temp_kf = model.temp_kf!!
    )

    override fun mapToDomainList(responses: List<MainResponse>): List<Main> {
        val result =  ArrayList<Main>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Main>): List<MainResponse> {
        val result =  ArrayList<MainResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}