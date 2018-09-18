package com.ahozyainov.cloudshare.presenter.base

import android.util.Log
import com.ahozyainov.cloudshare.model.Resp
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("services/rest/?method=flickr.people.getInfo&api_key=ccaf0957a411c28a2391d7cdc448d902&user_id=77825218@N04&format=json")
    fun getProfile(@Query("nojsoncallback") int: Int): Call<Resp>

    companion object Factory {
        fun create(): FlickrApiService {
            val gson = GsonBuilder()
                    .setLenient()
                    .create()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://api.flickr.com/")
                    .build()

            Log.d("retrofit ", "factory " + retrofit.baseUrl().toString())
            return retrofit.create(FlickrApiService::class.java)
        }
    }
}