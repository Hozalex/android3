package com.ahozyainov.cloudshare.presenter

import android.util.Log
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import rx.Observable

class RestPresenter : BaseRestPresenter<Char>() {

    override fun onNext(char: Char) {
       Log.d("obser", char.toString())
    }

    fun update(list: List<Char>) {
        viewState.startLoading()
        Observable.from(list).subscribe(this)

    }
}