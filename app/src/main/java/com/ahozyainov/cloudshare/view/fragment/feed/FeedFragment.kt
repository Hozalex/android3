package com.ahozyainov.cloudshare.view.fragment.feed

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.model.FeedViewModel
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentFeedLayout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun setItem(items: List<FeedViewModel>) {
        val feedAdapter = FeedAdapter(items)
        recycler_view_feed.adapter = feedAdapter
        recycler_view_feed.layoutManager = LinearLayoutManager(context)
    }

    override fun startLoading(string: String) {
        Toast.makeText(context, "Loading start", Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        Toast.makeText(context, "Loading end", Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Log.d("error", error)
    }

}