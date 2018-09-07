package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpView

interface BestRestView : MvpView {
    fun setButtonNum(viewIndex: Int, value: Int)
    fun startLoading()
    fun hideLoading()
    fun showError(error: String)

}