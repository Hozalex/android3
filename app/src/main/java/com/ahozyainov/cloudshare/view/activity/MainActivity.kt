package com.ahozyainov.cloudshare.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.ahozyainov.cloudshare.view.fragment.feed.FeedFragment
import com.ahozyainov.cloudshare.view.fragment.profile.ProfileFragment
import com.ahozyainov.cloudshare.view.fragment.search.SearchFragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.app_bar.*
import com.ahozyainov.cloudshare.R.id.action_feed as actionFeed
import com.ahozyainov.cloudshare.R.id.action_profile as actionProfile
import com.ahozyainov.cloudshare.R.id.action_search as actionSearch
import com.ahozyainov.cloudshare.R.id.bottom_navigation as bottomNavigation
import com.ahozyainov.cloudshare.R.id.frame_container as frameContainer
import com.ahozyainov.cloudshare.R.layout.activity_main as activityMain

class MainActivity : MvpAppCompatActivity() {

    private lateinit var activeFragment: Fragment
    private var fragmentTag = "active fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
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
                actionProfile -> placeFragment(ProfileFragment::class.qualifiedName)
                actionSearch -> placeFragment(SearchFragment::class.qualifiedName)
            }
            true
        }
    }

    private fun placeFragment(fragmentTagString: String?) {
        activeFragment = Fragment.instantiate(this, fragmentTagString, null)
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                        android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                .replace(frameContainer, activeFragment)
                .commit()
    }
}
