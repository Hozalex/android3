package com.ahozyainov.cloudshare.model

import com.google.gson.annotations.SerializedName

data class Person(

        @field:SerializedName("iconfarm")
        val iconfarm: Int? = null,

        @field:SerializedName("has_stats")
        val hasStats: String? = null,

        @field:SerializedName("nsid")
        val nsid: String? = null,

        @field:SerializedName("path_alias")
        val pathAlias: Any? = null,

        @field:SerializedName("iconserver")
        val iconserver: String? = null,

        @field:SerializedName("can_buy_pro")
        val canBuyPro: Int? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("ispro")
        val ispro: Int? = null,

        @field:SerializedName("username")
        val username: Username? = null
)