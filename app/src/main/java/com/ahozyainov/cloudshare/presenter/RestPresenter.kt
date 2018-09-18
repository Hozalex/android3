package com.ahozyainov.cloudshare.presenter

import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.ahozyainov.cloudshare.presenter.base.BaseRestView
import com.arellomobile.mvp.InjectViewState

@InjectViewState
class RestPresenter : BaseRestPresenter<Any, BaseRestView>() {
    override fun onNext(t: Any?) {
    }

    fun update(list: List<String>) {
    }
}