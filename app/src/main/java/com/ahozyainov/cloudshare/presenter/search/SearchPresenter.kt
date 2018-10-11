package com.ahozyainov.cloudshare.presenter.search

import android.util.Log
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.StringResourses
import com.ahozyainov.cloudshare.model.SearchViewModel
import com.ahozyainov.cloudshare.model.net.FlickrApiService
import com.ahozyainov.cloudshare.presenter.base.BasePresenterView
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class SearchPresenter : BaseRestPresenter<Any, BasePresenterView>() {

    @Inject
    lateinit var retrofit: Retrofit

    private val TAG = "search presenter"
    private val responseError = "response error"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        MainApplication.instance.getRetrofitComponent().injectToSearchPresenter(this)
    }

    fun update(searchText: String?) {
        try {
            val searchService = retrofit.create(FlickrApiService::class.java)
            searchService.getSearchView(StringResourses.NO_JSON_CALLBACK,
                    StringResourses.API_KEY,
                    StringResourses.EXTRAS,
                    StringResourses.REQ_FORMAT,
                    searchText!!,
                    StringResourses.REQ_TYPE)
                    .enqueue(object : Callback<SearchViewModel> {
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
        viewState.showData(searchViewModel!!.getUrlList(), searchViewModel.getDescriptionList())
    }

}