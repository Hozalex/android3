package com.ahozyainov.cloudshare.view.fragment

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.ahozyainov.cloudshare.R
import com.ahozyainov.cloudshare.presenter.RestPresenter
import com.ahozyainov.cloudshare.presenter.base.BaseRestView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.app_fragment.*

class FragmentOne : MvpAppCompatFragment(), BaseRestView {

    @InjectPresenter
    lateinit var presenter: RestPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_text.text = "Fragment One"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.app_fragment, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val alert = AlertDialog.Builder(context!!)
        val editText = EditText(context)
        alert.setTitle("Search")
                .setView(editText)
                .setPositiveButton("OK") { dialogInterface, i ->
                    if (editText.text.isNotEmpty()) {
                        presenter.update(editText.text.toList())
                    }
                }
        alert.show()
        return super.onOptionsItemSelected(item)
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}