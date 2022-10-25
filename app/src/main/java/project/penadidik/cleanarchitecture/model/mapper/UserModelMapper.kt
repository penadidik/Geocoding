package project.penadidik.cleanarchitecture.model.mapper

import project.penadidik.cleanarchitecture.base.BaseMapper
import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.model.UserModel
import javax.inject.Inject

class UserModelMapper @Inject constructor() : BaseMapper<User, UserModel> {
    override fun mapToPresentation(model: User): UserModel = UserModel(
        id = model.id,
        login = model.login,
        htmlUrl =  model.htmlUrl,
        avatar = model.avatar,
        isFavorite = model.isFavorite
    )

    override fun mapToDomain(modelItem: UserModel) = User(
        id = modelItem.id,
        login = modelItem.login,
        htmlUrl =  modelItem.htmlUrl,
        avatar = modelItem.avatar,
        isFavorite = modelItem.isFavorite
    )
}