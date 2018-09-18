package com.ahozyainov.cloudshare.presenter.profile

import android.util.Log
import com.ahozyainov.cloudshare.model.ProfileViewModel
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

    fun update() {
        try {
            flickrApiService = FlickrApiService.create()
            val call: Call<ProfileViewModel> = flickrApiService.getProfile()
            call.enqueue(object : Callback<ProfileViewModel> {
                override fun onResponse(call: Call<ProfileViewModel>, response: Response<ProfileViewModel>) {
                    if (response.isSuccessful) {
                        val profileViewModel: ProfileViewModel? = response.body()
                        viewState.startLoading(profileViewModel!!.photoUrl)
                    } else {
                        Log.d("onResponse", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ProfileViewModel>, t: Throwable) {
                    Log.d("onFailure", t.message)
                }

            })
        } catch (e: IOException) {
            Log.d("retrofit error", e.message)
            return
        }


    }
}