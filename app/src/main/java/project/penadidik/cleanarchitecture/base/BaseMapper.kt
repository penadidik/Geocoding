package project.penadidik.cleanarchitecture.base

import project.penadidik.cleanarchitecture.domain.model.Model

interface BaseMapper<M : Model, MI : BaseModel> {
    fun mapToPresentation(model: M): BaseModel

    fun mapToDomain(modelItem: MI): Model
}