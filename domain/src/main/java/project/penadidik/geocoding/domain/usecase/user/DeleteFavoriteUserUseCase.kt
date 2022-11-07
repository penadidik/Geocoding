package project.penadidik.geocoding.domain.usecase.user

import project.penadidik.geocoding.domain.repository.UserRepository
import project.penadidik.geocoding.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class DeleteFavoriteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<DeleteFavoriteUserUseCase.Params, Single<Boolean>>() {

    override fun createObservable(params: Params?): Single<Boolean> {
        return userRepository.deleteFavorite(idUser = params!!.idUser)
    }

    data class Params(val idUser: Int)
}