package com.ahozyainov.cloudshare.presenter.feed

import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter

class FeedPresenter : BaseRestPresenter<Any, FeedView>() {
    override fun onNext(t: Any?) {
    }

    override fun attachView(view: FeedView?) {
        super.attachView(view)
        update()
    }

    private fun update() {

    }
}