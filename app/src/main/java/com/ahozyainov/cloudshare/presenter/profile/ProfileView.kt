package com.ahozyainov.cloudshare.presenter.profile

import com.ahozyainov.cloudshare.presenter.base.BasePresenterView

interface ProfileView : BasePresenterView {
    fun setData(userName: String, url: String)
    override fun showData(urlItems: List<String>, descriptionItems: List<String>) {}
}