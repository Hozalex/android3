package com.ahozyainov.cloudshare.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.view.fragment.FragmentOne
import com.ahozyainov.cloudshare.view.fragment.FragmentThree
import com.ahozyainov.cloudshare.view.fragment.FragmentTwo
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.app_fragment.*

class MainActivity : MvpAppCompatActivity() {

    private lateinit var mFragmentManager: android.support.v4.app.FragmentManager
    private lateinit var mFragmentOne: FragmentOne
    private lateinit var mFragmentTwo: FragmentTwo
    private lateinit var mFragmentThree: FragmentThree
    private lateinit var mBottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariable()
        setSupportActionBar(toolbar)
        onNavigationItemSelected()
        mFragmentManager.beginTransaction().add(R.id.frame_container, mFragmentOne).commit()
    }

    private fun initVariable() {
        mBottomNavView = findViewById(R.id.bottom_navigation)
        mFragmentOne = FragmentOne()
        mFragmentTwo = FragmentTwo()
        mFragmentThree = FragmentThree()
        mFragmentManager = supportFragmentManager

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val alert = AlertDialog.Builder(this)
        val editText = EditText(this)
        alert.setTitle("Search")
                .setView(editText)
                .setPositiveButton("OK") { dialogInterface, i ->
                    if (editText.text.isNotEmpty()) {
                        fragment_text.text = editText.text.toString()
                    }
                }
        alert.show()
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigationItemSelected() {
        mBottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_one -> {
                    mFragmentManager.beginTransaction().replace(R.id.frame_container, mFragmentOne).addToBackStack(null).commit()
                }
                R.id.action_two -> {
                    mFragmentManager.beginTransaction().replace(R.id.frame_container, mFragmentTwo).addToBackStack(null).commit()
                }
                R.id.action_three -> {
                    mFragmentManager.beginTransaction().replace(R.id.frame_container, mFragmentThree).addToBackStack(null).commit()
                }
            }
            false
        }

    }

}
