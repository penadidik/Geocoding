package project.penadidik.cleanarchitecture.data.model.mapper

import project.penadidik.cleanarchitecture.data.base.EntityMapper
import project.penadidik.cleanarchitecture.data.model.UserEntity
import project.penadidik.cleanarchitecture.domain.model.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : EntityMapper<User, UserEntity> {
    override fun mapToDomain(entity: UserEntity): User = User(
        id = entity.id,
        login = entity.login,
        htmlUrl =  entity.htmlUrl,
        avatar = entity.avatar,
        isFavorite = entity.favorite
    )

    override fun mapToEntity(model: User): UserEntity = UserEntity(
        id = model.id,
        login = model.login,
        htmlUrl = model.htmlUrl,
        avatar = model.avatar,
        favorite = model.isFavorite
    )

    override fun mapToDomainList(entities: List<UserEntity>): List<User> {
        val result = ArrayList<User>()
        entities.forEach {
            result.add(mapToDomain(it))
        }

        return result;
    }

    override fun mapToEntities(modelList: List<User>): List<UserEntity> {
        val result = ArrayList<UserEntity>()
        modelList.forEach {
            result.add(mapToEntity(it))
        }

        return result;
    }
}