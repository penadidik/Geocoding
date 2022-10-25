package project.penadidik.cleanarchitecture.domain.usecase.user

import project.penadidik.cleanarchitecture.domain.model.UserFavorite
import project.penadidik.cleanarchitecture.domain.repository.UserRepository
import project.penadidik.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

open class GetAllUserFavoriteUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<GetAllUserFavoriteUseCase.Params, Single<List<UserFavorite>>>() {

    override fun createObservable(params: Params?): Single<List<UserFavorite>> {
        return userRepository.searchUsersFavorite()
    }

    override fun onCleared() {
    }

    class Params()

}