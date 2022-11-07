package project.penadidik.geocoding.domain.usecase.user

import project.penadidik.geocoding.domain.model.User
import project.penadidik.geocoding.domain.repository.UserRepository
import project.penadidik.geocoding.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class SetFavoriteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<SetFavoriteUserUseCase.Params, Single<User>>() {

    override fun createObservable(params: Params?): Single<User> {
        return userRepository.setFavorite(idUser = params!!.idUser, isEnabled = params.isEnabled)
    }

    data class Params(val idUser: Int, val isEnabled: Boolean)
}