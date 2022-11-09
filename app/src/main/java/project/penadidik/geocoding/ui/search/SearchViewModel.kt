package project.penadidik.geocoding.ui.search

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import project.penadidik.geocoding.base.BaseViewModel
import project.penadidik.geocoding.domain.model.Detail
import project.penadidik.geocoding.domain.usecase.GetDetailDirectUseCase
import project.penadidik.geocoding.domain.usecase.SearchDirectUseCase
import project.penadidik.geocoding.domain.usecase.SetFavoriteDirectUseCase
import project.penadidik.geocoding.extension.add
import project.penadidik.geocoding.ui.detail.DetailModelMapper
import project.penadidik.geocoding.util.RxUtils
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchDirectUseCase: SearchDirectUseCase,
    private val getDetailDirectUseCase: GetDetailDirectUseCase,
    private val setFavoriteDirectUseCase: SetFavoriteDirectUseCase,
    private val mapper: SearchModelMapper,
    private val mapperDetail: DetailModelMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<SearchModel>>()
    val query = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>().apply { postValue(false) }
    val page = MutableLiveData<Int>().apply { postValue(1) }
    val isRequesting = MutableLiveData<Boolean>().apply { postValue(false) }
    val isPaginating = MutableLiveData<Boolean>().apply { postValue(false) }
    val isLastPage = MutableLiveData<Boolean>().apply { postValue(false) }
    private val isDataEmpty = MutableLiveData<Boolean>().apply { postValue(false) }
    private val collectData = ArrayList<SearchModel>()
    private val limit = MutableLiveData<Int>().apply { postValue(5) }
    val openDetail = MutableLiveData<SearchModel>()

    fun searching(paginate: Boolean = false) {
        when (query.value?.isNotEmpty()) {
            true -> {

                if (!paginate) {
                    checkConnection()
                    isRequesting.value = true
                    isLastPage.value = false;
                    page.value = 1;
                    collectData.clear();
                }

                searchDirectUseCase.createObservable(SearchDirectUseCase.Params(query = query.value!!, limit = limit.value!!))
                    .compose(RxUtils.applySingleScheduler(loading))
                    .doFinally { loading.value = false }
                    .map { it.map { mapper.mapToPresentation(it) } }
                    .subscribe({ dataSearch ->
                        if (dataSearch.isNotEmpty()) {
                            isDataEmpty.value = false
                            collectData.addAll(dataSearch)
                            data.value = collectData

                            page.value = page.value?.plus(1)
                            isLastPage.value = dataSearch.size < 15
                        } else {
                            if (page.value == 1) isDataEmpty.value = true
                            isLastPage.value = true
                        }

                        isRequesting.value = false
                        isPaginating.value = false
                    }, {
                        Timber.e("Get search error: $it")
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

    fun setFavorite(model: SearchModel) {
        Timber.tag("SearchViewModel").d("[setFavorite] trigger")
        getDetailDirectUseCase.createObservable(GetDetailDirectUseCase.Params(lat = model.lat!!, lon = model.lon!!))
            .compose(RxUtils.applySingleScheduler(loading))
            .doFinally { loading.value = false }
            .map { it.map { mapperDetail.mapToPresentation(it) } }
            .subscribe({dataList ->
                val detailList = ArrayList<Detail>()
                dataList.forEach {
                    it.lat = model.lat
                    it.lon = model.lon

                    detailList.add(mapperDetail.mapToDomain(it))
                }
                Timber.tag("SearchViewModel").d("[setFavorite] success get detail")

                saveLocal(model, detailList)
            }, {
                Timber.e("Get detail error: $it")
                setThrowable(it)
            })
            .add(this)
    }

    private fun saveLocal(model: SearchModel, detailList: List<Detail>) {
        Timber.tag("SearchViewModel").d("[saveLocal] trigger")
        setFavoriteDirectUseCase.createObservable(
            SetFavoriteDirectUseCase.Params(
                direct = mapper.mapToDomain(
                    model
                ),
                details = detailList
            )
        )
            .compose(RxUtils.applySingleScheduler(loading))
            .doFinally { loading.value = false }
            .subscribe({
                val throwable = Throwable()
                if (it) {
                    Throwable("Success save as Favorite!")
                    Timber.tag("SearchViewModel").d("[saveLocal] Success save as Favorite!")
                }else {
                    Throwable("Failure save as Favorite!")
                    Timber.tag("SearchViewModel").d("[saveLocal] Failure save as Favorite!")
                }
                setThrowable(throwable)
            }, {
                Timber.e("Get detail error: $it")
                setThrowable(it)
            })
            .add(this)
    }

    @VisibleForTesting
    fun clear() {
        super.onCleared()
    }

}