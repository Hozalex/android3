package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

<<<<<<< HEAD
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProfileViewModel(@SerializedName("iconfarm")
                            @Expose
                            private var iconFarm: String? = null,
                            @SerializedName("iconserver")
                            @Expose
                            private var iconServer: String? = null,
                            @SerializedName("nsid")
                            @Expose
                            private var nsid: String? = null) {

    var photoUrl = "http://farm$iconFarm.staticflickr.com/$iconServer/buddyicons/$nsid.jpg"

}
=======
data class ProfileViewModel(val description: String)
>>>>>>> parent of 0e11294... add userPhoto
