package com.addam.skeletoncompose.features.demo.login

import androidx.lifecycle.ViewModel
import com.addam.skeletoncompose.rest.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Created by addam on 24/11/2022
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    val isFormValid = username.combine(password){ input1, input2 ->
        input1.isNotEmpty() && input2.isNotEmpty()
    }


}