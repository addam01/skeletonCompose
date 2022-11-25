package com.addam.skeletoncompose.core

import com.addam.skeletoncompose.features.demo.login.LoginActivity
import com.addam.skeletoncompose.features.demo.sample.BasicSampleActivity

/**
 * Created by addam on 24/11/2022
 */
class Router{
    enum class Destination{
        LOGIN,
        SAMPLE
    }

    enum class Parameter{

    }

    companion object{
        fun getClass(destination: Destination): Class<*>{
            return when(destination){
                Destination.LOGIN -> LoginActivity::class.java
                Destination.SAMPLE -> BasicSampleActivity::class.java
            }
        }
    }
}
