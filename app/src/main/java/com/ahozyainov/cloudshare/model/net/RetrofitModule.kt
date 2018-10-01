package com.ahozyainov.cloudshare.model.net

import com.ahozyainov.cloudshare.model.FeedViewModel
import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class RetrofitModule {

    private val apiKey = "ccaf0957a411c28a2391d7cdc448d902"
    var userId = "77825218@N04"
    private val format = "json"
    private val extras = "url_s"
    private val noJsonCallback = 1

    @Provides
    fun getRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.flickr.com/")
                .build()
    }

    @Provides
    fun getProfileCall(retrofit: Retrofit): Call<ProfileViewModel> {
        val flickrApiService = retrofit.create(FlickrApiService::class.java)
        return flickrApiService.getProfile(noJsonCallback, apiKey, userId, format)
    }

    @Provides
    fun getFeedCall(retrofit: Retrofit): Call<FeedViewModel> {
        val flickrApiService = retrofit.create(FlickrApiService::class.java)
        return flickrApiService.getFeedView(noJsonCallback, apiKey, extras, format)
    }
}