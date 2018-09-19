package com.ahozyainov.cloudshare.presenter.base

import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("services/rest/?method=flickr.people.getInfo")
    fun getProfile(@Query("nojsoncallback") int: Int,
                   @Query("api_key") apiKey: String,
                   @Query("user_id") userId: String,
                   @Query("format") format: String)
            : Call<ProfileViewModel>

    companion object Factory {
        fun create(): FlickrApiService {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://api.flickr.com/")
                    .build()
            return retrofit.create(FlickrApiService::class.java)
        }
    }
}