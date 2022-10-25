package project.penadidik.cleanarchitecture.ui.user

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import project.penadidik.cleanarchitecture.base.BaseViewModel
import project.penadidik.cleanarchitecture.domain.usecase.user.SearchUserUseCase
import project.penadidik.cleanarchitecture.domain.usecase.user.SetFavoriteUserUseCase
import project.penadidik.cleanarchitecture.extension.add
import project.penadidik.cleanarchitecture.model.UserModel
import project.penadidik.cleanarchitecture.model.mapper.UserModelMapper
import project.penadidik.cleanarchitecture.util.RxUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase,
    private val setFavoriteUserUseCase: SetFavoriteUserUseCase,
    private val mapper: UserModelMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<UserModel>>()
    val query = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }
    val page = MutableLiveData<Int>().apply { postValue(1) }
    val isRequesting = MutableLiveData<Boolean>().apply { postValue(false) }
    val isPaginating = MutableLiveData<Boolean>().apply { postValue(false) }
    val isLastPage = MutableLiveData<Boolean>().apply { postValue(false) }
    private val isDataEmpty = MutableLiveData<Boolean>().apply { postValue(false) }
    private val collectData = ArrayList<UserModel>()
    private val sort = MutableLiveData<String>().apply { postValue("") }

    fun searchUser(paginate: Boolean = false) {

        when(query.value?.isNotEmpty()) {
            true -> {

                if (!paginate) {
                    checkConnection()
                    isRequesting.value = true
                    isLastPage.value = false;
                    page.value = 1;
                    collectData.clear();
                }

                searchUserUseCase.createObservable(SearchUserUseCase.Params(query = query.value!!, pageNumber = page.value, sort = sort.value?:"", fromServer = isNetworkAvailable.value == true))
                    .compose(RxUtils.applySingleScheduler(loading))
                    .doFinally { loading.value = false }
                    .map { it.map { mapper.mapToPresentation(it) } }
                    .subscribe({ userList ->
                        if (userList.isNotEmpty()) {
                            isDataEmpty.value = false
                            collectData.addAll(userList)
                            data.value = collectData

                            page.value = page.value?.plus(1)
                            isLastPage.value = userList.size < 30
                        } else {
                            if (page.value == 1) isDataEmpty.value = true
                            isLastPage.value = true
                        }

                        isRequesting.value = false
                        isPaginating.value = false
                    }, {
                        Timber.e("Get user error: $it")
                        setThrowable(it)
                    })
                    .add(this)
            }

            false -> {
                isRequesting.value = false
                isPaginating.value = false
            }
        }
    }

    fun sort(isAscending: Boolean) {
        sort.value = if (isAscending) "ASC" else "DESC"
        searchUser()
    }

    fun setFavorite(idUser: Int, isEnabled: Boolean) {
        setFavoriteUserUseCase.createObservable(SetFavoriteUserUseCase.Params(idUser = idUser, isEnabled = isEnabled))
            .map { mapper.mapToPresentation(it) }
            .subscribe({ user ->
                val temporaryData = data.value
                temporaryData?.forEach {
                    it.isFavorite = user.isFavorite
                }
                data.value = temporaryData
                mUpdateRecycler.value = temporaryData?.indexOf(user)
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