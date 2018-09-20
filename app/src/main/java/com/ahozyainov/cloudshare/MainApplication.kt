package com.ahozyainov.cloudshare

import android.app.Application
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.ahozyainov.cloudshare.model.UserData
import com.ahozyainov.cloudshare.model.dao.ProfileDao

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "userData").build()
    }

    @Database(entities = [UserData::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun profileDao(): ProfileDao

    }
}