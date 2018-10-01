package com.ahozyainov.cloudshare

import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.ahozyainov.cloudshare.model.net.FlickrApiService
import com.ahozyainov.cloudshare.model.net.RetrofitModule
import com.ahozyainov.cloudshare.presenter.profile.ProfilePresenter
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Retrofit

class ProfilePresenterUnitTest {

    private val mockRetrofitModule = mock(RetrofitModule::class.java)!!
    private val mockProfilePresenter = mock(ProfilePresenter::class.java)!!

    private val mockUserId = "77825218@N05"
    private val apiKey = "ccaf0957a411c28a2391d7cdc448d902"
    private val format = "json"

    private lateinit var mockCall: Call<ProfileViewModel>


    @Test
    fun profilePresenterWrongUserIdTest() {
        mockCall = getProfileCall(mockRetrofitModule.getRetrofit())
        mockProfilePresenter.call = mockCall
        mockProfilePresenter.getProfileJson()
    }

    private fun getProfileCall(retrofit: Retrofit): Call<ProfileViewModel> {
        val flickrApiService = retrofit.create(FlickrApiService::class.java)
        return flickrApiService.getProfile(1, apiKey, mockUserId, format)
    }
}