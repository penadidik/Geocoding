package project.penadidik.cleanarchitecture.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.penadidik.cleanarchitecture.domain.exception.*
import project.penadidik.cleanarchitecture.domain.model.Dialog
import project.penadidik.cleanarchitecture.domain.model.Redirect
import project.penadidik.cleanarchitecture.domain.model.Tag
import project.penadidik.cleanarchitecture.domain.usecase.UseCase
import project.penadidik.cleanarchitecture.util.SingleLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel constructor(
    private vararg val useCases: UseCase<*, *>?
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val snackBarMessage = SingleLiveData<String>()
    val toastMessage = SingleLiveData<String>()
    val inlineException = SingleLiveData<List<Tag>>()
    val alertException = SingleLiveData<Pair<String?, String>>()
    val dialogException = SingleLiveData<Dialog>()
    val redirectException = SingleLiveData<Redirect>()
    val isNetworkAvailable = SingleLiveData<Boolean>()
    val checkConnection = MutableLiveData<Int>()
    val mUpdateRecycler = SingleLiveData<Int>()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun checkConnection() {
        checkConnection.value = 1
    }

    fun setThrowable(throwable: Throwable) {
        when (throwable) {
            is SnackBarException -> snackBarMessage.value = throwable.message
            is ToastException -> toastMessage.value = throwable.message
            is InlineException -> inlineException.value = throwable.tags.toList()
            is AlertException -> alertException.value = Pair(throwable.title, throwable.message)
            is DialogException -> dialogException.value = throwable.dialog
            is RedirectException -> redirectException.value = throwable.redirect
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        useCases.let { userCases ->
            if (userCases.isNotEmpty()) userCases.forEach { it?.onCleared() }
        }
        super.onCleared()
    }
}