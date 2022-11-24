package com.addam.skeletoncompose.rest.api.login

import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
class LoginRepository @Inject constructor(private val loginHelper: LoginHelper) {
    suspend fun getLogin() = loginHelper.getLogin()
}