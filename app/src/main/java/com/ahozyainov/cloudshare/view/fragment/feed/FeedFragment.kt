package com.ahozyainov.cloudshare.view.fragment.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.ahozyainov.cloudshare.presenter.feed.FeedPresenter
import com.ahozyainov.cloudshare.presenter.feed.FeedView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.image_recycler_feed.view.*
import com.ahozyainov.cloudshare.R.layout.fragment_feed as fragmentFeedLayout

class FeedFragment : MvpAppCompatFragment(), FeedView, FeedAdapter.OnFeedImageClickListener {


    @InjectPresenter
    lateinit var feedPresenter: FeedPresenter
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var urlList: List<String>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val feedView = inflater.inflate(fragmentFeedLayout, container, false)
        return feedView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun setItem(items: List<String>) {
        urlList = items
        feedAdapter = FeedAdapter(items, this)
        recycler_view_feed.adapter = feedAdapter
    }

    override fun showError(error: String) {
        Log.d("error", error)
    }

    override fun onFeedImageClick(imagePosition: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlList[imagePosition])
        startActivity(intent)
    }

}