package com.ahozyainov.cloudshare

import android.app.Application
import android.graphics.drawable.Drawable
import com.ahozyainov.cloudshare.model.net.DaggerRetrofitComponent
import com.ahozyainov.cloudshare.model.net.RetrofitComponent

class MainApplication : Application() {

    private val DBNAME = "userdatabase"

    companion object {
        lateinit var instance: MainApplication
    }

    private lateinit var retrofitComponent: RetrofitComponent
    private var fullImage: Drawable? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        retrofitComponent = DaggerRetrofitComponent.create()
    }

    fun getRetrofitComponent(): RetrofitComponent {
        return retrofitComponent
    }

    fun getFullImage(): Drawable? {
        return fullImage
    }

    fun setFullImage(drawable: Drawable) {
        fullImage = drawable
    }

}