package com.ahozyainov.cloudshare.view.activity

import android.os.Bundle
import android.widget.Toast
import com.ahozyainov.cloudshare.MainApplication
import com.ahozyainov.cloudshare.R
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.android.synthetic.main.image_full_screen.*

class FullImageActivity : MvpAppCompatActivity() {

    private val NOIMAGE = "no image"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_full_screen)

        val drawable = MainApplication.instance.getFullImage()
        if (drawable != null) {
            full_image.setImageDrawable(drawable)
        } else Toast.makeText(this, NOIMAGE, Toast.LENGTH_LONG).show()

    }

}