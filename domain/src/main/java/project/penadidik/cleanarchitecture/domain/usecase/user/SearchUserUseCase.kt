package project.penadidik.cleanarchitecture.domain.usecase.user

import project.penadidik.cleanarchitecture.domain.model.User
import project.penadidik.cleanarchitecture.domain.repository.UserRepository
import project.penadidik.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class SearchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<SearchUserUseCase.Params, Single<List<User>>>() {

    override fun createObservable(params: Params?): Single<List<User>> {
        val result: Single<List<User>> = if (params != null) {
            userRepository.searchUsers(query = params.query, page = params.pageNumber, sort = params.sort, fromServer = params.fromServer)
        } else Single.error(Throwable(""))

        return result
    }

    override fun onCleared() {
    }

    data class Params(val query: String, val pageNumber: Int? = 1, val sort: String, val fromServer: Boolean)
}