package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpPresenter
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

abstract class BaseRestPresenter<T, V : BaseRestView> : MvpPresenter<V>(), Subscriber<T> {

    override fun onError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

    override fun onSubscribe(s: Subscription) = s.request(Long.MAX_VALUE)

    override fun onComplete() {}
}