package com.addam.skeletoncompose.rest.api.login

import com.addam.skeletoncompose.rest.models.LoginResponse
import retrofit2.Response

/**
 * Created by addam on 24/11/2022
 */
interface LoginHelper {
    suspend fun getLogin(): Response<LoginResponse>
}