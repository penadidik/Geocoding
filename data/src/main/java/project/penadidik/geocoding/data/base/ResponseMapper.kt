package project.penadidik.geocoding.data.base

import project.penadidik.geocoding.domain.model.Model

interface ResponseMapper<M : Model, ME : ModelResponse> {
    fun mapToDomain(response: ME): M

    fun mapToResponse(model: M): ME

    fun mapToDomainList(responses: List<ME>) : List<M>

    fun mapToResponses(modelList: List<M>) : List<ME>
}