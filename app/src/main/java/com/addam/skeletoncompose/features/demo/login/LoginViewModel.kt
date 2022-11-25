package com.addam.skeletoncompose.features.demo.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.addam.skeletoncompose.rest.api.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    val isFormValid = username.combine(password){ input1, input2 ->
        input1.isNotEmpty() && input2.isNotEmpty()
    }

    fun getLogin() = viewModelScope.launch {
        loginRepository.getLogin().let {
            if(it.isSuccessful){
                it.body().let {
                    Log.d("Login", it.toString())
                }
            }else{
                Log.e("Error", it.errorBody().toString())
            }
        }
    }
}