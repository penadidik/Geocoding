package project.penadidik.geocoding.data.remote.mapper

import project.penadidik.geocoding.data.base.ResponseMapper
import project.penadidik.geocoding.data.remote.response.CloudsResponse
import project.penadidik.geocoding.domain.model.Clouds
import javax.inject.Inject

class CloudsResponseMapper @Inject constructor() : ResponseMapper<Clouds, CloudsResponse> {
    override fun mapToDomain(response: CloudsResponse): Clouds {
        val clouds = Clouds()
        clouds.all = response.all

        return clouds
    }

    override fun mapToResponse(model: Clouds): CloudsResponse = CloudsResponse(all = model.all!!)

    override fun mapToDomainList(responses: List<CloudsResponse>): List<Clouds> {
        val result =  ArrayList<Clouds>()
        responses.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToResponses(modelList: List<Clouds>): List<CloudsResponse> {
        val result =  ArrayList<CloudsResponse>()
        modelList.forEach {
            result.add(mapToResponse(it))
        }
        return result
    }
}