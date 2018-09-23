package com.ahozyainov.cloudshare

import android.app.Application
import android.arch.persistence.room.Room
import com.ahozyainov.cloudshare.model.dao.AppDatabase
import com.ahozyainov.cloudshare.model.net.DaggerRetrofitComponent
import com.ahozyainov.cloudshare.model.net.RetrofitComponent


class MainApplication : Application() {

    private val DBNAME = "userdatabase"

    companion object {
        lateinit var instance: MainApplication
    }

    private lateinit var database: AppDatabase
    private lateinit var retrofitComponent: RetrofitComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, DBNAME)
                .build()
        retrofitComponent = DaggerRetrofitComponent.create()
    }

    fun getDatabase(): AppDatabase {
        return database
    }

    fun getRetrofitComponent(): RetrofitComponent {
        return retrofitComponent
    }

}