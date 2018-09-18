package com.ahozyainov.cloudshare.presenter.profile

import android.util.Log
import com.ahozyainov.cloudshare.model.Resp
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.ahozyainov.cloudshare.presenter.base.FlickrApiService
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@InjectViewState
class ProfilePresenter : BaseRestPresenter<Any, ProfileView>() {
    private lateinit var flickrApiService: FlickrApiService

    override fun onNext(t: Any?) {
    }

    override fun attachView(view: ProfileView?) {
        super.attachView(view)
        update()
    }

    fun update() {
        try {
            flickrApiService = FlickrApiService.create()
            val call: Call<Resp> = flickrApiService.getProfile(1)
            Log.d("retrofit ", "start call " + call.request().url().toString())
            call.enqueue(object : Callback<Resp> {
                override fun onResponse(call: Call<Resp>, response: Response<Resp>) {
                    if (response.isSuccessful) {
                        Log.d("retrofit ", "onResponse succ " + response.code().toString() + " " + call.request().url().toString())
                        val resp: Resp? = response.body()
                        Log.d("retrofit", "pesponse body ${response.body().toString()}")
                        Log.d("retrofit", "photoUrl " + resp!!.getPhotoUrl())
                        viewState.startLoading(resp!!.getPhotoUrl())
                    } else {
                        Log.d("retrofit ", "onResponse err " + response.code().toString() + " " + call.request().url().toString())
                    }
                }

                override fun onFailure(call: Call<Resp>, t: Throwable) {
                    Log.d("retrofit ", "failure " + t.message)
                }

            })
        } catch (e: IOException) {
            Log.d("retrofit ", "error " + e.message)
            return
        }


    }
}