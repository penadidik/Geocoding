package project.penadidik.geocoding.base

import project.penadidik.geocoding.domain.model.Model

interface BaseMapper<M : Model, MI : BaseModel> {
    fun mapToPresentation(model: M): BaseModel

    fun mapToDomain(modelItem: MI): Model
}