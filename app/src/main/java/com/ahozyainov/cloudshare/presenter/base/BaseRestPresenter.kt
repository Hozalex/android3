package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpPresenter

abstract class BaseRestPresenter<T, V : BasePresenterView> : MvpPresenter<V>() {

    fun onError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

}