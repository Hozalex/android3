package com.ahozyainov.cloudshare.model

data class ProfileViewModel(
        private var iconfarm: String? = null,
        private var iconserver: String? = null,
        private var nsid: String? = null) {

    var photoUrl = "http://farm$iconfarm.staticflickr.com/$iconserver/buddyicons/$nsid.jpg"

}