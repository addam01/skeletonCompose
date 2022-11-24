package com.addam.skeletoncompose.rest.models

import com.google.gson.annotations.SerializedName

data class PostResponse (
        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String
        )
