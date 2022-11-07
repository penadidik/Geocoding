package project.penadidik.geocoding.model.mapper

import project.penadidik.geocoding.base.BaseMapper
import project.penadidik.geocoding.domain.model.UserFavorite
import project.penadidik.geocoding.model.FavoriteModel
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