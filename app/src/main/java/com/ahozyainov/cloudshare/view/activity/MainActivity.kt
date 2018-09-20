package com.ahozyainov.cloudshare.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.ahozyainov.cloudshare.view.fragment.feed.FeedFragment
import com.ahozyainov.cloudshare.view.fragment.profile.ProfileFragment
import com.ahozyainov.cloudshare.view.fragment.search.SearchFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.app_bar.*
import com.ahozyainov.cloudshare.R.id.action_feed as actionFeed
import com.ahozyainov.cloudshare.R.id.action_profile as actionProfile
import com.ahozyainov.cloudshare.R.id.action_search as actionSearch
import com.ahozyainov.cloudshare.R.id.bottom_navigation as bottomNavigation
import com.ahozyainov.cloudshare.R.id.frame_container as frameContainer
import com.ahozyainov.cloudshare.R.layout.activity_main as activityMain
import com.ahozyainov.cloudshare.R.menu.app_bar_menu as barMenu

class MainActivity : MvpAppCompatActivity() {

    lateinit var appFragmentManager: FragmentManager
    private val fragmentTag = "active fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMain)
        initVariable()

        setSupportActionBar(toolbar)

        onNavigationItemSelected()

        var activeFragment = appFragmentManager.findFragmentByTag(fragmentTag)

        if (activeFragment == null) {
            activeFragment = FeedFragment()
        }
        appFragmentManager.beginTransaction().add(frameContainer, activeFragment, fragmentTag).commit()
    }

    private fun initVariable() {
        appFragmentManager = supportFragmentManager

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(barMenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val alert = AlertDialog.Builder(this)
        val editText = EditText(this)
        alert.setTitle("Search")
                .setView(editText)
                .setPositiveButton("OK") { dialogInterface, i ->
                    if (editText.text.isNotEmpty()) {
//                        if (fragmentFeed.isVisible) {
//                            mRestPresenter.update(arrayListOf(editText.text.toString()))
//                        }
                    }
                }
        alert.show()
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigationItemSelected() {
        val bottomNavigationView: BottomNavigationView = findViewById(bottomNavigation)
        bottomNavigationView.animate()
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                actionFeed -> {
                    placeFragment(FeedFragment::class.qualifiedName)
                }
                actionProfile -> {
                    placeFragment(ProfileFragment::class.qualifiedName)
                }
                actionSearch -> {
                    placeFragment(SearchFragment::class.qualifiedName)
                }
            }
            true
        }

    }

    private fun placeFragment(fragmentTag: String?) {
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                .replace(frameContainer, Fragment.instantiate(this, fragmentTag, null))
                .commit()
    }


}
