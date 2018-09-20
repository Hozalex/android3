package com.ahozyainov.cloudshare.view.fragment.profile

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ahozyainov.cloudshare.presenter.profile.ProfilePresenter
import com.ahozyainov.cloudshare.presenter.profile.ProfileView
import com.ahozyainov.cloudshare.view.GlideApp
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile.image_view_profile as avatarImageView
import kotlinx.android.synthetic.main.fragment_profile.text_view_profile as userNameTextView
import com.ahozyainov.cloudshare.R.layout.fragment_profile as fragmentProfileLayout

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var profilePresenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(fragmentProfileLayout, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val connectivityManager: ConnectivityManager = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            profilePresenter.update()
        } else {
            Toast.makeText(context, "Connect to Internet is unavailable", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setData(userName: String, url: String) {
        GlideApp.with(this).load(url).into(avatarImageView)
        userNameTextView.text = userName
    }

    override fun startLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

}