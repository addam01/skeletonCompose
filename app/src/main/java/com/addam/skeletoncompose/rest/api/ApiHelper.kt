package com.addam.skeletoncompose.rest.api

import com.addam.skeletoncompose.rest.models.PostResponse
import retrofit2.Response

/**
 * Created by addam on 24/11/2022
 */
interface ApiHelper {
    suspend fun getPosts(): Response<List<PostResponse>>
}