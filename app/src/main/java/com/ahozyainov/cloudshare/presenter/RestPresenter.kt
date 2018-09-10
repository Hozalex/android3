package com.ahozyainov.cloudshare.presenter

import android.util.Log
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.arellomobile.mvp.InjectViewState
import rx.Observable

@InjectViewState
class RestPresenter : BaseRestPresenter<String>() {

    override fun onNext(string: String) {
        Log.d("rest_presenter", string)
        viewState.startLoading(string)
    }

    fun update(list: List<String>) {

        Observable.from(list).subscribe(this)
    }
}