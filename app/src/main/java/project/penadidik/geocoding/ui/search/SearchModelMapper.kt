package project.penadidik.geocoding.ui.search

import project.penadidik.geocoding.base.BaseMapper
import project.penadidik.geocoding.domain.model.Direct
import javax.inject.Inject

class SearchModelMapper @Inject constructor() : BaseMapper<Direct, SearchModel>  {
    override fun mapToPresentation(model: Direct): SearchModel = SearchModel(
        name = model.name,
        lat = model.lat,
        lon = model.lon,
        country = model.country,
        state = model.state
    )

    override fun mapToDomain(modelItem: SearchModel): Direct = Direct(
        name = modelItem.name,
        lat = modelItem.lat,
        lon = modelItem.lon,
        country = modelItem.country,
        state = modelItem.state
    )

}