package project.penadidik.geocoding.ui.search

import project.penadidik.geocoding.base.BaseMapper
import project.penadidik.geocoding.domain.model.Direct
import javax.inject.Inject

class SearchModelMapper @Inject constructor() : BaseMapper<Direct, SearchModel>  {
    override fun mapToPresentation(model: Direct): SearchModel {
        val searchModel = SearchModel()
        searchModel.apply {
            name = model.name
            lat = model.lat
            lon = model.lon
            country = model.country
            state = model.state
        }

        return searchModel
    }

    override fun mapToDomain(modelItem: SearchModel): Direct {
        val direct = Direct()
        direct.apply {
            name = modelItem.name
            lat = modelItem.lat
            lon = modelItem.lon
            country = modelItem.country
            state = modelItem.state
        }

        return direct
    }

}