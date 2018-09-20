package com.ahozyainov.cloudshare.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.ahozyainov.cloudshare.model.UserData
import io.reactivex.Single

@Dao
interface ProfileDao {

    @Query("Select * from userdata where nsid = :userNsid")
    fun getUserByNsid(userNsid: String): Single<UserData>

    @Insert(onConflict = REPLACE)
    fun insertUser(user: UserData)

    @Delete
    fun deleteAll(user: UserData)
}