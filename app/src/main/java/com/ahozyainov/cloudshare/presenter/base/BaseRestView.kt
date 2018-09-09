package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpView

interface BaseRestView : MvpView {
    fun startLoading()
    fun hideLoading()
    fun showError(error: String)

}