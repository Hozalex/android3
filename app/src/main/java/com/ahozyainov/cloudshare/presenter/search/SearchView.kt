package com.ahozyainov.cloudshare.presenter.search

import com.ahozyainov.cloudshare.presenter.base.BaseRestView

interface SearchView : BaseRestView {
    fun setItem(items: List<String>)
}