package com.ahozyainov.cloudshare.model.net

import com.ahozyainov.cloudshare.model.FeedViewModel
import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.ahozyainov.cloudshare.model.SearchViewModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("services/rest/?method=flickr.people.getInfo")
    fun getProfile(@Query("nojsoncallback") nojsoncallback: Int,
                   @Query("api_key") apiKey: String,
                   @Query("user_id") userId: String,
                   @Query("format") format: String)
            : Call<ProfileViewModel>

    @GET("services/rest/?method=flickr.interestingness.getList")
    fun getFeedView(@Query("nojsoncallback") nojsoncallback: Int,
                    @Query("api_key") apiKey: String,
                    @Query("extras") extras: String,
                    @Query("format") format: String)
            : Call<FeedViewModel>

    @GET("services/rest/?method=flickr.photos.search")
    fun getSearchView(@Query("nojsoncallback") nojsoncallback: Int,
                      @Query("api_key") apiKey: String,
                      @Query("extras") extras: String,
                      @Query("format") format: String,
                      @Query("text") searchText: String,
                      @Query("content_type") type: Int)
            : Call<SearchViewModel>
}