package com.ahozyainov.cloudshare.presenter.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BasePresenterView : MvpView {
    fun showError(error: String)
    fun showData(urlItems: List<String>, descriptionItems: List<String>)
}