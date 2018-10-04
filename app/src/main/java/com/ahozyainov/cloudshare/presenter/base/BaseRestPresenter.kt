package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpPresenter
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class BaseRestPresenter<T, V : BaseRestView> : MvpPresenter<V>() {

    fun onError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

}