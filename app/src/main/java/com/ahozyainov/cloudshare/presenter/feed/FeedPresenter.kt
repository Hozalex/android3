package com.ahozyainov.cloudshare.presenter.feed

import android.util.Log
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.model.FeedViewModel
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Flowable

@InjectViewState
class FeedPresenter : BaseRestPresenter<Any, FeedView>() {

    lateinit var feedViewModel: FeedViewModel

    override fun onNext(t: Any) {
        viewState.setItem(arrayListOf(FeedViewModel("none", "none", t)))
    }

    override fun attachView(view: FeedView?) {
        super.attachView(view)
        update()
    }

    private fun update() {
        Flowable.just(R.mipmap.example).subscribe(this)
    }
}