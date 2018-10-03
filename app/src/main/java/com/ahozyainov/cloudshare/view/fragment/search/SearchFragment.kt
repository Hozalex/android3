package com.ahozyainov.cloudshare.view.fragment.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.Toast
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import com.ahozyainov.cloudshare.presenter.search.SearchView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_search.*
import com.ahozyainov.cloudshare.R.layout.fragment_search as fragmentSearchLayout

class SearchFragment : MvpAppCompatFragment(), SearchView, SearchAdapter.OnSearchImageClickListener {

    @InjectPresenter
    lateinit var searchPesenter: SearchPresenter

    lateinit var searchAdapter: SearchAdapter
    lateinit var urlList: List<String>

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.app_bar_menu, menu)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.app_bar_search)?.actionView as android.support.v7.widget.SearchView)
                .apply {
                    setSearchableInfo(searchManager
                            .getSearchableInfo(activity?.componentName))
                }
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
}