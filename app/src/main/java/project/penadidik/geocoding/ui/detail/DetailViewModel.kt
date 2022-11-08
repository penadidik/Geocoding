package project.penadidik.geocoding.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import project.penadidik.geocoding.base.BaseViewModel
import project.penadidik.geocoding.domain.usecase.GetDetailDirectUseCase
import project.penadidik.geocoding.extension.add
import project.penadidik.geocoding.ui.search.SearchModel
import project.penadidik.geocoding.util.RxUtils
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailDirectUseCase: GetDetailDirectUseCase,
    private val mapper: DetailModelMapper
) : BaseViewModel() {

    val data = MutableLiveData<List<DetailModel>>()

    val state = ObservableField<String>()
    val country = ObservableField<String>()
    val lat = ObservableField<String>()
    val lon = ObservableField<String>()

    val loading = MutableLiveData<Boolean>().apply { postValue(false) }
    val page = MutableLiveData<Int>().apply { postValue(1) }
    val isRequesting = MutableLiveData<Boolean>().apply { postValue(false) }
    val isPaginating = MutableLiveData<Boolean>().apply { postValue(false) }
    val isLastPage = MutableLiveData<Boolean>().apply { postValue(false) }
    private val isDataEmpty = MutableLiveData<Boolean>().apply { postValue(false) }
    private val collectData = ArrayList<DetailModel>()

    fun getDetail(lat: Double, lon: Double) {
        getDetailDirectUseCase.createObservable(GetDetailDirectUseCase.Params(lat = lat, lon = lon))
            .compose(RxUtils.applySingleScheduler(loading))
            .doFinally { loading.value = false }
            .map { it.map { mapper.mapToPresentation(it) } }
            .subscribe({dataList ->
                if (dataList.isNotEmpty()) {
                    isDataEmpty.value = false
                    collectData.addAll(dataList)
                    data.value = collectData

                    page.value = page.value?.plus(1)
                    isLastPage.value = dataList.size < 15
                } else {
                    if (page.value == 1) isDataEmpty.value = true
                    isLastPage.value = true
                }

                isRequesting.value = false
                isPaginating.value = false
            }, {
                Timber.e("Get detail error: $it")
                setThrowable(it)
            })
            .add(this)
    }

}