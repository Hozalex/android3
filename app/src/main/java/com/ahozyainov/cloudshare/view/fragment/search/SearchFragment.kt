package com.ahozyainov.cloudshare.view.fragment.search

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import com.ahozyainov.cloudshare.presenter.search.SearchView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_search.*
import android.support.v7.widget.SearchView as WidgetSearchView
import com.ahozyainov.cloudshare.R.id.app_bar_search as appBarSearchView
import com.ahozyainov.cloudshare.R.layout.fragment_search as fragmentSearchLayout

class SearchFragment : MvpAppCompatFragment(), SearchView,
        SearchAdapter.OnSearchImageClickListener, WidgetSearchView.OnQueryTextListener {

    @InjectPresenter
    lateinit var searchPesenter: SearchPresenter

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var urlList: List<String>
    private lateinit var searchView: WidgetSearchView
    private var recyclerViewState: Parcelable? = null
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var connectionError = "Connect to Internet is unavailable"
    private var LAYOUT_STATE = "layout state"

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.app_bar_menu, menu)
        val searchItem = menu?.findItem(appBarSearchView)
        searchView = searchItem?.actionView as WidgetSearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentSearchLayout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        if (recyclerViewState != null) {
            layoutManager.onRestoreInstanceState(recyclerViewState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        layoutManager = recycler_view_search.layoutManager!!
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
        if (items.isNotEmpty()) {
            urlList = items
            searchAdapter = SearchAdapter(items, this)
            recycler_view_search.adapter = searchAdapter
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recycler_view_search.layoutManager = GridLayoutManager(context, 3)
            } else recycler_view_search.layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onSearchImageClick(imagePosition: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlList[imagePosition])
        startActivity(intent)
    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        if (checkInternet()) {
            searchPesenter.update(text)
        } else {
            Toast.makeText(context, connectionError, Toast.LENGTH_SHORT).show()
        }
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
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