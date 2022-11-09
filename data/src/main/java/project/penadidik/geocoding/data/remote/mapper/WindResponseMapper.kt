package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.WindResponse
import project.penadidik.geocoding.domain.model.Wind
import javax.inject.Inject

class WindResponseMapper @Inject constructor() : ResponseMapper<Wind, WindResponse> {
    override fun mapToDomain(response: WindResponse): Wind {
        val wind = Wind()
        wind.apply {
            speed = response.speed
            deg = response.deg
            gust = response.gust
        }

        return wind
    }

    override fun mapToResponse(model: Wind): WindResponse = WindResponse(
        speed = model.speed!!,
        deg = model.deg!!,
        gust = model.gust!!
    )

    override fun mapToDomainList(responses: List<WindResponse>): List<Wind> {
        val result =  ArrayList<Wind>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Wind>): List<WindResponse> {
        val result =  ArrayList<WindResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}