package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.SerializedName

data class UserNameViewModel(@SerializedName("_content") var userName: String? = null) {
}