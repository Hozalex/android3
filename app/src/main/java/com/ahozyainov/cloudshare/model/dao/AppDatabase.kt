package com.ahozyainov.cloudshare.model.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ahozyainov.cloudshare.model.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}