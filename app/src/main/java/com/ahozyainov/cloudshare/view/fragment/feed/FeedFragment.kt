package com.ahozyainov.cloudshare.view.fragment.feed

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.presenter.feed.FeedPresenter
import com.ahozyainov.cloudshare.presenter.feed.FeedView
import com.ahozyainov.cloudshare.view.activity.FullImageActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_feed.*
import com.ahozyainov.cloudshare.R.layout.fragment_feed as fragmentFeedLayout

class FeedFragment : MvpAppCompatFragment(), FeedView, FeedAdapter.OnFeedImageClickListener {

    @InjectPresenter
    lateinit var feedPresenter: FeedPresenter

    private lateinit var feedAdapter: FeedAdapter
    private lateinit var urlList: List<String>
    private var recyclerViewState: Parcelable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val connectionError = "Connect to Internet is unavailable"
    private val LAYOUT_STATE = "layout State"

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentFeedLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (recyclerViewState != null) {
            layoutManager.onRestoreInstanceState(recyclerViewState)
        } else {
            feedUpdate()
        }

    }

    private fun feedUpdate() {
        if (checkInternet()) {
            feedPresenter.update()
        } else {
            Toast.makeText(context, connectionError, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        layoutManager = recycler_view_feed.layoutManager!!
        recyclerViewState = layoutManager.onSaveInstanceState()!!
        outState.putParcelable(LAYOUT_STATE, recyclerViewState)

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            recyclerViewState = savedInstanceState.getParcelable(LAYOUT_STATE)!!
        }
    }

    override fun setItem(items: List<String>) {
        urlList = items
        feedAdapter = FeedAdapter(items, this)
        recycler_view_feed.adapter = feedAdapter
    }

    override fun showError(error: String) {
        Log.d("error", error)
    }


    override fun onFeedImageClick(image: ImageView) {
        MainApplication.instance.setFullImage(image.drawable)
        val intent = Intent(activity, FullImageActivity::class.java)
        startActivity(intent)
    }

    private fun checkInternet(): Boolean {
        var isConnected = false
        val connectivityManager: ConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            isConnected = true
        }
        return isConnected
    }

}