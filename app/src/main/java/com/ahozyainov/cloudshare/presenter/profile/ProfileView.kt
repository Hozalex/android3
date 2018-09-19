package com.ahozyainov.cloudshare.presenter.profile

import com.ahozyainov.cloudshare.presenter.base.BaseRestView

interface ProfileView : BaseRestView {
    fun setData(userName: String, url:String)
}