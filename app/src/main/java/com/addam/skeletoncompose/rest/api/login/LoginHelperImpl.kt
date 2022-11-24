package com.addam.skeletoncompose.rest.api.login

import com.addam.skeletoncompose.rest.api.ApiServices
import com.addam.skeletoncompose.rest.models.LoginResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
class LoginHelperImpl @Inject constructor(private val apiServices: ApiServices): LoginHelper {
    override suspend fun getLogin(): Response<LoginResponse>
        = apiServices.getLogin()
}