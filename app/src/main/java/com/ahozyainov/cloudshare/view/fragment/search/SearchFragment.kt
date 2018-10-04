package com.ahozyainov.cloudshare.view.fragment.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.view.menu.MenuItemImpl
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.R.id.app_bar_search
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import com.ahozyainov.cloudshare.presenter.search.SearchView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_search.*
import android.support.v7.widget.SearchView as WidgetSearchView
import com.ahozyainov.cloudshare.R.layout.fragment_search as fragmentSearchLayout

class SearchFragment : MvpAppCompatFragment(), SearchView,
        SearchAdapter.OnSearchImageClickListener, WidgetSearchView.OnQueryTextListener {

    @InjectPresenter
    lateinit var searchPesenter: SearchPresenter

    private lateinit var searchAdapter: SearchAdapter
    private lateinit var urlList: List<String>
    private lateinit var searchView: WidgetSearchView

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.app_bar_menu, menu)
        val searchItem = menu?.findItem(app_bar_search)
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

    override fun setItem(items: List<String>) {
        if (items.isNotEmpty()) {
            urlList = items
            searchAdapter = SearchAdapter(items, this)
            recycler_view_search.adapter = searchAdapter
            recycler_view_search.layoutManager = GridLayoutManager(context, 3)
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

    override fun onQueryTextSubmit(p0: String?): Boolean {
        Log.d("searchView", searchView.query.toString())
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}