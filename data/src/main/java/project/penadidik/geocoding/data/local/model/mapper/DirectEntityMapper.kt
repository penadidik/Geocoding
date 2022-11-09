package project.penadidik.geocoding.data.local.model.mapper

import project.penadidik.geocoding.data.base.EntityMapper
import project.penadidik.geocoding.data.local.model.DirectEntity
import project.penadidik.geocoding.domain.model.Direct
import javax.inject.Inject

class DirectEntityMapper @Inject constructor() : EntityMapper<Direct, DirectEntity> {
    override fun mapToDomain(entity: DirectEntity): Direct {
        val directFavorite = Direct()
        directFavorite.name = entity.name
        directFavorite.lat = entity.lat
        directFavorite.lon = entity.lon
        directFavorite.country = entity.country
        directFavorite.state = entity.state

        return directFavorite
    }

    override fun mapToEntity(model: Direct): DirectEntity {
        val directEntity = DirectEntity()
        directEntity.name = model.name
        directEntity.lat = model.lat
        directEntity.lon = model.lon
        directEntity.country = model.country
        directEntity.state = model.state

        return directEntity
    }

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