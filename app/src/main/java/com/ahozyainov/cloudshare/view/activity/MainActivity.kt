package com.ahozyainov.cloudshare.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.ahozyainov.cloudshare.R.id.action_feed as actionFeed
import com.ahozyainov.cloudshare.R.id.action_profile as actionProfile
import com.ahozyainov.cloudshare.R.id.action_search as actionSearch
import com.ahozyainov.cloudshare.R.id.frame_container as frameContainer
import com.ahozyainov.cloudshare.R.menu.app_bar_menu as barMenu
import com.ahozyainov.cloudshare.R.layout.activity_main as activityMain
import com.ahozyainov.cloudshare.R.id.bottom_navigation as bottomNavigation
import com.ahozyainov.cloudshare.presenter.RestPresenter
import com.ahozyainov.cloudshare.presenter.base.BaseRestView
import com.ahozyainov.cloudshare.view.fragment.feed.FragmentFeed
import com.ahozyainov.cloudshare.view.fragment.profile.FragmentProfile
import com.ahozyainov.cloudshare.view.fragment.search.FragmentSearch
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : MvpAppCompatActivity(), BaseRestView {

    @InjectPresenter
    lateinit var restPresenter: RestPresenter

    private lateinit var startFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMain)

        initVariable()

        setSupportActionBar(toolbar)

        onNavigationItemSelected()

        supportFragmentManager.beginTransaction().add(frameContainer, startFragment).commit()
    }

    private fun initVariable() {
        startFragment = FragmentFeed()
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
                    supportFragmentManager.beginTransaction().replace(frameContainer, FragmentFeed())
                            .commit()
                }
                actionProfile -> {
                    supportFragmentManager.beginTransaction().replace(frameContainer, FragmentProfile())
                            .commit()
                }
                actionSearch -> {
                    supportFragmentManager.beginTransaction().replace(frameContainer, FragmentSearch())
                            .commit()
                }
            }
            true
        }

    }

    override fun startLoading(string: String) {

    }

    override fun hideLoading() {
        Toast.makeText(this, "Hide Loading", Toast.LENGTH_SHORT).show()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }


}
