package com.ahozyainov.cloudshare

import android.app.Application
import android.arch.persistence.room.Room
import com.ahozyainov.cloudshare.model.dao.AppDatabase

class MainApplication : Application() {

    companion object {
        lateinit var instance: MainApplication
    }

    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "userdatabase")
                .build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }

}