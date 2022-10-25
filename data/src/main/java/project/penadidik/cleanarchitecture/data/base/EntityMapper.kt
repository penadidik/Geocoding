package project.penadidik.cleanarchitecture.data.base

import project.penadidik.cleanarchitecture.domain.model.Model

interface EntityMapper<M : Model, ME : project.penadidik.cleanarchitecture.data.base.ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME

    fun mapToDomainList(entities: List<ME>) : List<M>

    fun mapToEntities(modelList: List<M>) : List<ME>
}