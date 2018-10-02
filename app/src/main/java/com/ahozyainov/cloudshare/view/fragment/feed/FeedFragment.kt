package com.ahozyainov.cloudshare.view.fragment.feed

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.ahozyainov.cloudshare.R.id.recycler_view_feed
import com.ahozyainov.cloudshare.presenter.feed.FeedPresenter
import com.ahozyainov.cloudshare.presenter.feed.FeedView
import com.ahozyainov.cloudshare.view.GlideApp
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import com.ahozyainov.cloudshare.R.layout.fragment_feed as fragmentFeedLayout

class FeedFragment : MvpAppCompatFragment(), FeedView {

    @InjectPresenter
    lateinit var feedPresenter: FeedPresenter
    lateinit var feedAdapter: FeedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val feedView = inflater.inflate(fragmentFeedLayout, container, false)
        return feedView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun setItem(items: List<String>) {
        feedAdapter = FeedAdapter(items)
        recycler_view_feed.adapter = feedAdapter
    }

    override fun showError(error: String) {
        Log.d("error", error)
    }

}