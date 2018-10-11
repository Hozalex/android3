package com.ahozyainov.cloudshare.model.net

import com.ahozyainov.cloudshare.presenter.feed.FeedPresenter
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun injectToFeedPresenter(feedPresenter: FeedPresenter)
    fun injectToSearchPresenter(searchPresenter: SearchPresenter)
}