package com.ahozyainov.cloudshare.presenter.base

import com.ahozyainov.cloudshare.model.Model
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import rx.Observer

@InjectViewState
abstract class BaseRestPresenter<T> : MvpPresenter<BestRestView>(), Observer<T> {
    override fun onError(e: Throwable) {
        viewState.showError(e.localizedMessage)
    }

    override fun onCompleted() {
        viewState.hideLoading()w
    }

    private lateinit var mModel: Model

    override fun attachView(view: BestRestView?) {
        super.attachView(view)
        if (mModel == null) mModel = Model()
    }

    private fun calcNewModelValue(index: Int): Int {
        return mModel.getElementValue(index) + 1
    }

    fun buttonClick(btnIndex: Int) {
        var newValue: Int
        when (btnIndex) {
            1 -> {
                newValue = calcNewModelValue(0)
                mModel.setElementValue(0, newValue)
                viewState.setButtonNum(1, newValue)
            }
            2 -> {
                newValue = calcNewModelValue(1)
                mModel.setElementValue(1, newValue)
                viewState.setButtonNum(2, newValue)
            }
            3 -> {
                newValue = calcNewModelValue(2)
                mModel.setElementValue(2, newValue)
                viewState.setButtonNum(3, newValue)
            }

        }

    }
}