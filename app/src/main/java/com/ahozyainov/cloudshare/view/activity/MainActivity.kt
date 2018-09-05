package com.ahozyainov.cloudshare.view.activity

import android.app.Fragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.view.fragment.FragmentOne
import com.ahozyainov.cloudshare.view.fragment.FragmentThree
import com.ahozyainov.cloudshare.view.fragment.FragmentTwo
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity()
{
    private lateinit var mFragmentManager: FragmentManager
    private lateinit var mFragmentOne: FragmentOne
    private lateinit var mFragmentTwo: FragmentTwo
    private lateinit var mFragmentThree: FragmentThree
    private lateinit var mBottomNavView: BottomNavigationView
    private lateinit var fragmentActive: Fragment


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initVariable()
        setSupportActionBar(toolbar)
        initFragment()
        onNavigationItemSelected()
    }

    private fun initVariable()
    {
        mBottomNavView = findViewById(R.id.bottom_navigation)
        mFragmentOne = FragmentOne()
        mFragmentTwo = FragmentTwo()
        mFragmentThree = FragmentThree()
        mFragmentManager = fragmentManager
        fragmentActive = mFragmentOne
    }

    private fun initFragment()
    {
        mFragmentManager.beginTransaction().add(R.id.frame_container, mFragmentThree).hide(mFragmentThree).commit()
        mFragmentManager.beginTransaction().add(R.id.frame_container, mFragmentTwo).hide(mFragmentTwo).commit()
        mFragmentManager.beginTransaction().add(R.id.frame_container, mFragmentOne).commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        Toast.makeText(this, "search menu", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigationItemSelected()
    {
        mBottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.action_one ->
                {
                    mFragmentManager.beginTransaction().hide(fragmentActive).show(mFragmentOne).commit()
                    fragmentActive = mFragmentOne
                }
                R.id.action_two ->
                {
                    mFragmentManager.beginTransaction().hide(fragmentActive).show(mFragmentTwo).commit()
                    fragmentActive = mFragmentTwo
                }
                R.id.action_three ->
                {
                    mFragmentManager.beginTransaction().hide(fragmentActive).show(mFragmentThree).commit()
                    fragmentActive = mFragmentThree
                }
            }
            false
        }

    }

}
