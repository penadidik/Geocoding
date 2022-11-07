package project.penadidik.geocoding.domain.usecase.user

import project.penadidik.geocoding.domain.model.User
import project.penadidik.geocoding.domain.repository.UserRepository
import project.penadidik.geocoding.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class FindUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<FindUserUseCase.Params?, Single<User>>() {

    override fun createObservable(params: Params?): Single<User> {
        if (params != null) {
            return userRepository.getUser(params.userId, params.fromServer)
        }

        return Single.error(Throwable("Params input not valid"))
    }

    data class Params(val userId: Int, val fromServer: Boolean)
}