package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseRestView : MvpView {
    fun startLoading()
    fun hideLoading()
    fun showError(error: String)
}