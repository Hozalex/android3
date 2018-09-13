package com.ahozyainov.cloudshare.presenter.feed

import android.util.Log
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.model.FeedViewModel
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import io.reactivex.Flowable

class FeedPresenter : BaseRestPresenter<Any, FeedView>() {

    lateinit var feedViewModel: FeedViewModel

    override fun onNext(t: Any) {
        Log.d("xxx", "onNext")
        feedViewModel = FeedViewModel("none", "none", t)
        Log.d("xxx", feedViewModel.address.toString())
        val items = arrayListOf(feedViewModel)
        Log.d("xxx", viewState.toString())
        viewState.setItem(items)
    }

    override fun attachView(view: FeedView?) {
        super.attachView(view)
        update()
    }

    private fun update() {
        Log.d("xxx", "Start observer")
        Flowable.just(R.mipmap.example).subscribe(this)
    }
}