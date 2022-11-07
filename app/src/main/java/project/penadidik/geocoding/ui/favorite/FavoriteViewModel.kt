package project.penadidik.geocoding.ui.favorite

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import project.penadidik.geocoding.base.BaseViewModel
import project.penadidik.geocoding.domain.exception.ToastException
import project.penadidik.geocoding.domain.usecase.user.DeleteFavoriteUserUseCase
import project.penadidik.geocoding.domain.usecase.user.GetAllUserFavoriteUseCase
import project.penadidik.geocoding.extension.add
import project.penadidik.geocoding.model.FavoriteModel
import project.penadidik.geocoding.model.mapper.FavoriteModelMapper
import project.penadidik.geocoding.util.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllUserFavoriteUseCase: GetAllUserFavoriteUseCase,
    private val deleteFavoriteUserUseCase: DeleteFavoriteUserUseCase,
    private val mapper: FavoriteModelMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<FavoriteModel>>()
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }

    init {
        searchFavorite()
    }

    fun searchFavorite() {
        getAllUserFavoriteUseCase.createObservable(GetAllUserFavoriteUseCase.Params())
            .compose(RxUtils.applySingleScheduler(loading))
            .doFinally { loading.value = false }
            .map { it.map { mapper.mapToPresentation(it) } }
            .subscribe({ list ->
                data.value = list
            }, {
                Timber.e("Get user error: $it")
                setThrowable(it)
            })
            .add(this)
    }

    fun deleteFavorite(userId: Int) {
        deleteFavoriteUserUseCase.createObservable(DeleteFavoriteUserUseCase.Params(idUser = userId))
            .compose(RxUtils.applySingleScheduler(loading))
            .doFinally { loading.value = false }
            .subscribe({ response ->

                if (response) {
                    val favorites = ArrayList<FavoriteModel>()
                    data.value?.toCollection(favorites)
                    val favorite = favorites.find { it.id == userId }
                    favorites.remove(favorite)
                    data.value = favorites

                    setThrowable(ToastException(200, "Success delete!"))
                }
            }, {
                Timber.e("Get user error: $it")
                setThrowable(it)
            })
            .add(this)
    }

    @VisibleForTesting
    fun clear() {
        super.onCleared()
    }
}