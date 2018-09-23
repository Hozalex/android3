package com.ahozyainov.cloudshare.model.net

import com.ahozyainov.cloudshare.presenter.feed.FeedPresenter
import com.ahozyainov.cloudshare.presenter.profile.ProfilePresenter
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import dagger.Component

@Component(modules = [RetrofitModule::class])
interface RetrofitComponent {
    fun injectToProfilePresenter(profilePresenter: ProfilePresenter)
    fun injectToFeedPresenter(feedPresenter: FeedPresenter)
    fun injectToSearchPresenter(searchPresenter: SearchPresenter)
}