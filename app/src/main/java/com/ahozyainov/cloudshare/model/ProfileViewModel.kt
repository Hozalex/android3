package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.SerializedName

data class ProfileViewModel(val person: Person? = null) {

    fun getPhotoUrl(): String {
        return "http://farm${person?.iconfarm}.staticflickr.com/${person?.iconserver}/buddyicons/${person?.nsid}.jpg"
    }

    data class Person(
            val iconfarm: Int? = null,
            val nsid: String? = null,
            val iconserver: String? = null,
            val username: Username? = null
    )

    data class Username(
            @SerializedName("_content")
            val content: String? = null
    )
}