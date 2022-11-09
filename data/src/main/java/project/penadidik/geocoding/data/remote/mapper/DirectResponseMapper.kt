package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.DirectResponse
import project.penadidik.geocoding.domain.model.Direct
import javax.inject.Inject

class DirectResponseMapper @Inject constructor() : ResponseMapper<Direct, DirectResponse> {
    override fun mapToDomain(response: DirectResponse): Direct {
        val direct = Direct()
        direct.apply {
            name = response.name
            lat = response.lat
            lon = response.lon
            country = response.country
            state = response.state
        }

        return direct
    }

    override fun mapToResponse(model: Direct): DirectResponse = DirectResponse(
        name = model.name!!,
        lat = model.lat!!,
        lon = model.lon!!,
        country = model.country!!,
        state = model.state!!
    )

    override fun mapToDomainList(responses: List<DirectResponse>): List<Direct> {
        val result =  ArrayList<Direct>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Direct>): List<DirectResponse> {
        val result =  ArrayList<DirectResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}