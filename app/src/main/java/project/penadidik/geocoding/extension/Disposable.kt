package project.penadidik.geocoding.extension

import project.penadidik.geocoding.base.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.add(viewModel: BaseViewModel) {
    viewModel.addDisposable(this)
}