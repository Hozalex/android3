package com.ahozyainov.cloudshare.model.net

import com.ahozyainov.cloudshare.StringResourses
import com.ahozyainov.cloudshare.model.FeedViewModel
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

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
    fun getFeedCall(retrofit: Retrofit): Call<FeedViewModel> {
        val flickrApiService = retrofit.create(FlickrApiService::class.java)
        return flickrApiService.getFeedView(StringResourses.NO_JSON_CALLBACK,
                StringResourses.API_KEY,
                StringResourses.EXTRAS,
                StringResourses.REQ_FORMAT)
    }
}