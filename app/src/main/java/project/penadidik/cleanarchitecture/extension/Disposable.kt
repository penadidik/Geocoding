package project.penadidik.cleanarchitecture.extension

import project.penadidik.cleanarchitecture.base.BaseViewModel
import io.reactivex.disposables.Disposable

fun Disposable.add(viewModel: BaseViewModel) {
    viewModel.addDisposable(this)
}