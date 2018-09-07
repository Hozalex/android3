package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import rx.Observer

@InjectViewState
abstract class BaseRestPresenter<T> : MvpPresenter<BestRestView>(), Observer<T> {

    override fun onError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

    override fun onCompleted() {
        viewState.hideLoading()
    }

}