package com.ahozyainov.cloudshare.presenter.profile

import android.util.Log
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.model.ProfileViewModel
import com.ahozyainov.cloudshare.model.UserData
import com.ahozyainov.cloudshare.model.dao.AppDatabase
import com.ahozyainov.cloudshare.presenter.base.BaseRestPresenter
import com.ahozyainov.cloudshare.model.net.FlickrApiService
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@InjectViewState
class ProfilePresenter : BaseRestPresenter<Any, ProfileView>() {

    @Inject
    lateinit var call: Call<ProfileViewModel>

    private lateinit var flickrApiService: FlickrApiService
    private val TAG = "profile presenter"
    private val onResponseErr = "onResponse ProfilePresenter Error"
    private val onFailure = "onFailure ProfilePresenter Error"
    private val userId = "77825218@N04"
    private val db: AppDatabase = MainApplication.instance.getDatabase()
    private val retrofitComponent = MainApplication.instance.getRetrofitComponent()
    lateinit var user: UserData

    override fun onNext(t: Any?) {
    }

    override fun attachView(view: ProfileView?) {
        super.attachView(view)
        retrofitComponent.injectToProfilePresenter(this)
    }

    fun update() {
        checkDataFromDb()
    }

    private fun checkDataFromDb() {
        db.profileDao().getUserByNsid(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<UserData>() {
                    override fun onSuccess(t: UserData) {
                        setDataToFragment(t.userName, t.url)
                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, "check DB error")
                        getProfileJson()
                    }
                })
    }

    private fun getProfileJson() {
        try {
            call.enqueue(object : Callback<ProfileViewModel> {
                override fun onResponse(call: Call<ProfileViewModel>,
                                        response: Response<ProfileViewModel>) {
                    if (response.isSuccessful) {
                        val profileViewModel: ProfileViewModel? = response.body()
                        setDataToUserData(profileViewModel)
                        setDataToFragment(profileViewModel?.person?.username?.content!!, profileViewModel.getPhotoUrl())
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

    private fun setDataToUserData(profileViewModel: ProfileViewModel?) {
        user = UserData(profileViewModel?.person?.nsid!!,
                profileViewModel.getPhotoUrl(),
                profileViewModel.person.username?.content!!)
        insertUserDataToDb(user)
    }

    private fun insertUserDataToDb(user: UserData) {
        Completable.fromAction { db.profileDao().insertUser(user) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun setDataToFragment(userName: String, url: String) {
        viewState.setData(userName, url)
    }

}