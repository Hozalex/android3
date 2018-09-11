package com.ahozyainov.cloudshare.view.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahozyainov.cloudshare.R.layout.fragment_profile as fragmentProfileLayout
import com.arellomobile.mvp.MvpAppCompatFragment


class FragmentProfile : MvpAppCompatFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(fragmentProfileLayout, container, false)
    }

}