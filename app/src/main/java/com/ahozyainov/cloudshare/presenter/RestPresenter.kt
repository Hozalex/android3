package com.ahozyainov.cloudshare.presenter

import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import rx.Observable

class RestPresenter : BaseRestPresenter<String>() {
    override fun onNext(t: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun update() {
        viewState.startLoading()
        Observable.just("", "")
                .subscribe(this)
    }
}