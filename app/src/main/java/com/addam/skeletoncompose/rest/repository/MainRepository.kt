package com.addam.skeletoncompose.rest.repository

import com.addam.skeletoncompose.rest.api.ApiHelper
import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getPosts() = apiHelper.getPosts()
}