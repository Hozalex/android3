package com.ahozyainov.cloudshare.presenter.base

import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.ahozyainov.cloudshare.model.UserNameViewModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FlickrApiService {

    @GET("flickr.people.getInfo&api_key=ccaf0957a411c28a2391d7cdc448d902&user_id=77825218@N04&format=json")
    fun getProfile(): Call<ProfileViewModel>

    @GET("flickr.people.getInfo&api_key=ccaf0957a411c28a2391d7cdc448d902&user_id=77825218@N04&format=json")
    fun getUserName(): Call<UserNameViewModel>

    companion object Factory {
        fun create(): FlickrApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.flickr.com/services/rest/?method=")
                    .build()

            return retrofit.create(FlickrApiService::class.java)

        }

    }
}