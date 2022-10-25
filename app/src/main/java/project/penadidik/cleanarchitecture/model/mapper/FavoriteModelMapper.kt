package project.penadidik.cleanarchitecture.model.mapper

import project.penadidik.cleanarchitecture.base.BaseMapper
import project.penadidik.cleanarchitecture.domain.model.UserFavorite
import project.penadidik.cleanarchitecture.model.FavoriteModel
import javax.inject.Inject

class FavoriteModelMapper @Inject constructor() : BaseMapper<UserFavorite, FavoriteModel> {
    override fun mapToPresentation(model: UserFavorite): FavoriteModel = FavoriteModel(
        id = model.id,
        login = model.login,
        htmlUrl =  model.htmlUrl,
        avatar = model.avatar
    )

    override fun mapToDomain(modelItem: FavoriteModel) = UserFavorite(
        id = modelItem.id,
        login = modelItem.login,
        htmlUrl =  modelItem.htmlUrl,
        avatar = modelItem.avatar
    )
}