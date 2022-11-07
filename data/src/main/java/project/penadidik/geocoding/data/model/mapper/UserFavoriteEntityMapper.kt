package project.penadidik.geocoding.data.model.mapper

import project.penadidik.geocoding.data.base.EntityMapper
import project.penadidik.geocoding.data.model.UserFavoriteEntity
import project.penadidik.geocoding.domain.model.UserFavorite
import javax.inject.Inject

class UserFavoriteEntityMapper @Inject constructor() : EntityMapper<UserFavorite, UserFavoriteEntity> {
    override fun mapToDomain(entity: UserFavoriteEntity): UserFavorite = UserFavorite(
        id = entity.id,
        login = entity.login,
        htmlUrl =  entity.htmlUrl,
        avatar = entity.avatar
    )

    override fun mapToEntity(model: UserFavorite): UserFavoriteEntity = UserFavoriteEntity(
        id = model.id,
        login = model.login,
        htmlUrl = model.htmlUrl,
        avatar = model.avatar
    )

    override fun mapToDomainList(entities: List<UserFavoriteEntity>): List<UserFavorite> {
        val result = ArrayList<UserFavorite>()
        entities.forEach {
            result.add(mapToDomain(it))
        }

        return result;
    }

    override fun mapToEntities(modelList: List<UserFavorite>): List<UserFavoriteEntity> {
        val result = ArrayList<UserFavoriteEntity>()
        modelList.forEach {
            result.add(mapToEntity(it))
        }

        return result;
    }
}