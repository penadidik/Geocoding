package project.penadidik.geocoding.data.model.mapper

import project.penadidik.geocoding.data.base.EntityMapper
import project.penadidik.geocoding.data.model.DirectEntity
import project.penadidik.geocoding.domain.model.Direct
import javax.inject.Inject

class DirectEntityMapper @Inject constructor() : EntityMapper<Direct, DirectEntity> {
    override fun mapToDomain(entity: DirectEntity): Direct = Direct(
        name = entity.name,
        lat = entity.lat,
        lon = entity.lon,
        country = entity.country,
        state = entity.state
    )

    override fun mapToEntity(model: Direct): DirectEntity = DirectEntity(
        name = model.name,
        lat = model.lat,
        lon = model.lon,
        country = model.country,
        state = model.state
    )

    override fun mapToDomainList(entities: List<DirectEntity>): List<Direct> {
        val result =  ArrayList<Direct>()
        entities.forEach {
            result.add(mapToDomain(it))
        }
        return result
    }

    override fun mapToEntities(modelList: List<Direct>): List<DirectEntity> {
        val result =  ArrayList<DirectEntity>()
        modelList.forEach {
            result.add(mapToEntity(it))
        }
        return result
    }
}