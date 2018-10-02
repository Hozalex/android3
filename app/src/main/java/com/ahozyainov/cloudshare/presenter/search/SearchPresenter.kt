package com.ahozyainov.cloudshare.presenter.search

import android.util.Log
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.model.SearchViewModel
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class SearchPresenter : BaseRestPresenter<Any, SearchView>() {

    @Inject
    lateinit var call: Call<SearchViewModel>

    private val TAG = "search presenter"

    override fun onNext(t: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        MainApplication.instance.getRetrofitComponent().injectToSearchPresenter(this)
    }

    fun update(searchString: String) {
        try {
            call.enqueue(object : Callback<SearchViewModel>{
                override fun onFailure(call: Call<SearchViewModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<SearchViewModel>, response: Response<SearchViewModel>) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

        } catch (e: IOException) {
            Log.d(TAG, e.message)
        }

    }
}