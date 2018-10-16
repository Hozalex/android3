package com.ahozyainov.cloudshare.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.view.fragment.feed.FeedFragment
import com.ahozyainov.cloudshare.view.fragment.search.SearchFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.app_bar.*
import com.ahozyainov.cloudshare.R.id.action_feed as actionFeed
import com.ahozyainov.cloudshare.R.id.action_search as actionSearch
import com.ahozyainov.cloudshare.R.id.bottom_navigation as bottomNavigation
import com.ahozyainov.cloudshare.R.id.frame_container as frameContainer
import com.ahozyainov.cloudshare.R.layout.activity_main as activityMain

class MainActivity : MvpAppCompatActivity() {

    private lateinit var activeFragment: Fragment
    private var fragmentTag = "active fragment"
    private var firebaseAnalytics: FirebaseAnalytics? = null
    private val REQUEST_CODE = 99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        setContentView(activityMain)

        setSupportActionBar(toolbar)

        onNavigationItemSelected()

        if (savedInstanceState == null) {
            activeFragment = FeedFragment()
            supportFragmentManager.beginTransaction()
                    .add(frameContainer, activeFragment, fragmentTag)
                    .commit()
        }
    }

    private fun onNavigationItemSelected() {
        val bottomNavigationView: BottomNavigationView = findViewById(bottomNavigation)
        bottomNavigationView.animate()
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                actionFeed -> placeFragment(FeedFragment::class.qualifiedName)
                actionSearch -> {
                    checkPermission()
                    placeFragment(SearchFragment::class.qualifiedName)
                }
            }
            true
        }
    }

    private fun placeFragment(fragmentTagString: String?) {
        activeFragment = Fragment.instantiate(this, fragmentTagString, null)
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(frameContainer, activeFragment)
                .commit()
    }

    private fun checkPermission() {
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE)
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

}
