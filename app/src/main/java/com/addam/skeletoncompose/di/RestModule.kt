package com.addam.skeletoncompose.di

import com.addam.skeletoncompose.rest.api.ApiHelper
import com.addam.skeletoncompose.rest.api.ApiHelperImpl
import com.addam.skeletoncompose.rest.api.login.LoginHelper
import com.addam.skeletoncompose.rest.api.login.LoginHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by addam on 25/11/2022
 */
@Module
@InstallIn(SingletonComponent::class)
class RestModule {
    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideLoginHelper(loginHelper: LoginHelperImpl): LoginHelper = loginHelper
}