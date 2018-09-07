package com.ahozyainov.cloudshare.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahozyainov.cloudshare.R
import com.arellomobile.mvp.MvpAppCompatFragment
import kotlinx.android.synthetic.main.app_fragment.*

class FragmentThree : MvpAppCompatFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_text.text = "Fragment Three"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.app_fragment, container, false)
    }
}