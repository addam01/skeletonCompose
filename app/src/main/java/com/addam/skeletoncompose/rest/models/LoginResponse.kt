package com.addam.skeletoncompose.rest.models

import com.google.gson.annotations.SerializedName

/**
 * Created by addam on 24/11/2022
 */
data class LoginResponse (
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
        )