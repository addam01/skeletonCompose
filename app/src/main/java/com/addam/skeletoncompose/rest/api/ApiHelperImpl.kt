package com.addam.skeletoncompose.rest.api

import com.addam.skeletoncompose.rest.models.PostResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
class ApiHelperImpl @Inject constructor(private val apiServices: ApiServices): ApiHelper {
    override suspend fun getPosts(): Response<List<PostResponse>>
    = apiServices.getPosts()

}