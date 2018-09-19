package com.ahozyainov.cloudshare.presenter.profile

import android.app.Application
import android.util.Log
import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.ahozyainov.cloudshare.presenter.base.FlickrApiService
import com.ahozyainov.cloudshare.view.GlideApp
import com.arellomobile.mvp.InjectViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

@InjectViewState
class ProfilePresenter : BaseRestPresenter<Any, ProfileView>() {

    private lateinit var flickrApiService: FlickrApiService
    private val TAG = "retrofit"
    private val onResponseErr = "onResponse error"
    private val onFailure = "onFailure"
    private val apiKey = "ccaf0957a411c28a2391d7cdc448d902"
    private val userId = "77825218@N04"
    private val format = "json"
    private val noJsonCallback = 1

    override fun onNext(t: Any?) {
    }

    fun update() {
        getProfileJson()
    }

       private fun getProfileJson() {
        try {
            flickrApiService = FlickrApiService.create()
            val call: Call<ProfileViewModel> = flickrApiService
                    .getProfile(noJsonCallback, apiKey, userId, format)

            call.enqueue(object : Callback<ProfileViewModel> {
                override fun onResponse(call: Call<ProfileViewModel>,
                                        response: Response<ProfileViewModel>) {
                    if (response.isSuccessful) {
                        val profileViewModel: ProfileViewModel? = response.body()
                        setDataToFragment(profileViewModel)
                    } else {
                        viewState.showError(onResponseErr)
                        Log.d(TAG, "$onResponseErr ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ProfileViewModel>, t: Throwable) {
                    viewState.showError("$onFailure ${t.message}")
                    Log.d(TAG, "$onFailure ${t.message}")
                }
            })
        } catch (e: IOException) {
            Log.d(TAG, e.message)
            return
        }
    }

    fun setDataToFragment(profileViewModel: ProfileViewModel?) {
        viewState.setData(profileViewModel?.person?.username?.content!!,profileViewModel.getPhotoUrl())
    }
}