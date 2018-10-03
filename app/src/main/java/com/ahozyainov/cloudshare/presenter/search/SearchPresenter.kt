package com.ahozyainov.cloudshare.presenter.search

import android.util.Log
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.model.SearchViewModel
import com.ahozyainov.cloudshare.model.net.RetrofitModule
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
    private val responseError = "response error"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        MainApplication.instance.getRetrofitComponent().injectToSearchPresenter(this)
        update()

    }

    private fun update() {
        try {
            call.enqueue(object : Callback<SearchViewModel> {
                override fun onFailure(call: Call<SearchViewModel>, t: Throwable) {
                    viewState.showError(t.message!!)
                }

                override fun onResponse(call: Call<SearchViewModel>,
                                        response: Response<SearchViewModel>) {
                    if (response.isSuccessful) {
                        val searchViewModel: SearchViewModel? = response.body()
                        setDataToSearchFragment(searchViewModel)
                    } else {
                        Log.d(TAG, "$responseError ${response.code()}")
                    }

                }
            })

        } catch (e: IOException) {
            Log.d(TAG, e.message)
        }

    }

    private fun setDataToSearchFragment(searchViewModel: SearchViewModel?) {
        viewState.setItem(searchViewModel!!.getUrlList())

    }

    override fun onNext(t: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}