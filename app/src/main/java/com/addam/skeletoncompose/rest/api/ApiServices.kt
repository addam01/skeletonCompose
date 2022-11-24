package com.addam.skeletoncompose.rest.api

import com.addam.skeletoncompose.rest.models.LoginResponse
import com.addam.skeletoncompose.rest.models.PostResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by addam on 24/11/2022
 */
interface ApiServices {
    @GET("login")
    suspend fun getLogin(): Response<LoginResponse>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>
}