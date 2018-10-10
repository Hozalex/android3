package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.SerializedName

data class SearchViewModel(val photos: Photos? = null) {

    data class Photos(val photo: List<PhotoItem?>? = null)
    data class PhotoItem(val title: String = "",
                         @SerializedName("url_m") val url: String = "")

    fun getUrlList(): List<String> {
        val urlList = mutableListOf<String>()
        if (photos?.photo!!.isNotEmpty()) {
            for (photo: PhotoItem? in photos.photo) {
                urlList.add(photo!!.url)
            }
        }
        return urlList
    }

    fun getDescriptionList(): List<String> {
        val descriptionList = mutableListOf<String>()
        if (photos?.photo!!.isNotEmpty()) {
            for (photo: PhotoItem? in photos.photo) {
                descriptionList.add(photo!!.title)
            }
        }
        return descriptionList
    }
}