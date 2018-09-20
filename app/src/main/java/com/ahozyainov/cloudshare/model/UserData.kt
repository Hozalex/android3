package com.ahozyainov.cloudshare.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userdata")
data class UserData(@ColumnInfo
                    var nsid: String,
                    @ColumnInfo(name = "avatarUrl")
                    var url: String,
                    @ColumnInfo
                    var userName: String) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}


