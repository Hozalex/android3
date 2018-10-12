package com.ahozyainov.cloudshare.view.fragment.search

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.presenter.base.BasePresenterView
import com.ahozyainov.cloudshare.presenter.search.SearchPresenter
import com.ahozyainov.cloudshare.view.activity.FullImageActivity
import com.ahozyainov.cloudshare.view.activity.MainActivity
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import io.reactivex.rxkotlin.toObservable
import kotlinx.android.synthetic.main.fragment_search.*
import android.support.v7.widget.SearchView as WidgetSearchView
import com.ahozyainov.cloudshare.R.id.app_bar_search as appBarSearchView
import com.ahozyainov.cloudshare.R.layout.fragment_search as fragmentSearchLayout

class SearchFragment : MvpAppCompatFragment(), BasePresenterView,
        SearchAdapter.OnSearchImageClickListener, WidgetSearchView.OnQueryTextListener {

    @InjectPresenter
    lateinit var searchPesenter: SearchPresenter

    private lateinit var geocoder: Geocoder
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var urlList: List<String>
    private lateinit var descriptionList: List<String>
    private lateinit var searchView: WidgetSearchView
    private var connectionError = "Connect to Internet is unavailable"
    private val DESCRIPTION = "description"
    private val DEFAULT_SEARCH_REQUEST = "nature"


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.app_bar_menu, menu)
        val searchItem = menu?.findItem(appBarSearchView)
        searchView = searchItem?.actionView as WidgetSearchView
        searchView.setOnQueryTextListener(this)
        checkPermission()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun checkPermission() {
        try {
            if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                showQuery(DEFAULT_SEARCH_REQUEST)
            } else {
                getLocation()
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun getLocation() {
        val addresses: List<Address>
        geocoder = Geocoder(context)
        val location = LocationManager.NETWORK_PROVIDER
        val lm = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val lastKnownLocation: Location = lm.getLastKnownLocation(location)
        addresses = geocoder.getFromLocation(lastKnownLocation.latitude, lastKnownLocation.longitude, 1)
        showQuery(addresses[0].locality)
    }

    private fun showQuery(query: String) {
        searchView.setQuery(query, true)
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

    override fun showData(urlItems: List<String>, descriptionItems: List<String>) {
        if (urlItems.isNotEmpty()) {
            urlList = urlItems
            descriptionList = descriptionItems
            searchAdapter = SearchAdapter(urlItems, this)
            recycler_view_search.adapter = searchAdapter
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                recycler_view_search.layoutManager = GridLayoutManager(context, 3)
            } else recycler_view_search.layoutManager = GridLayoutManager(context, 4)
        }
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onSearchImageClick(imageView: ImageView, imagePosition: Int) {
        MainApplication.instance.setFullImage(imageView.drawable.mutate())
        val intent = Intent(context, FullImageActivity::class.java)
        intent.putExtra(DESCRIPTION, descriptionList[imagePosition])
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