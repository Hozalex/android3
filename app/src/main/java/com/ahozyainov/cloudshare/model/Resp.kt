package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.SerializedName

data class Resp(

        val person: Person? = null
) {
    fun getPhotoUrl(): String {
        return "http://farm${person?.iconfarm}.staticflickr.com/${person?.iconserver}/buddyicons/${person?.nsid}.jpg"
    }
}