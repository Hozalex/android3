package com.ahozyainov.cloudshare.presenter.feed

import android.util.Log
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.model.FeedViewModel
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class FeedPresenter : BaseRestPresenter<Any, FeedView>() {

    @Inject
    lateinit var call: Call<FeedViewModel>

    val TAG = "feedPresenter"
    val responseError = "feed response error"

    fun update() {
        MainApplication.instance.getRetrofitComponent().injectToFeedPresenter(this)
        try {
            call.enqueue(object : Callback<FeedViewModel> {
                override fun onResponse(call: Call<FeedViewModel>,
                                        response: Response<FeedViewModel>) {
                    if (response.isSuccessful) {
                        val feedViewModel: FeedViewModel? = response.body()
                        setDataToFeedFragment(feedViewModel)
                    } else {
                        Log.d(TAG, responseError)
                    }
                }

                override fun onFailure(call: Call<FeedViewModel>, t: Throwable) {
                    Log.d(TAG, t.message)
                }
            })
        } catch (e: IOException) {
            Log.d(TAG, e.message)
        }
    }

    private fun setDataToFeedFragment(feedViewModel: FeedViewModel?) {
        viewState.setItem(feedViewModel!!.getUrlList())
    }
}