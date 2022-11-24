@file:OptIn(ExperimentalMaterial3Api::class)

package com.addam.skeletoncompose.features.demo.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.addam.skeletoncompose.R
import com.addam.skeletoncompose.ui.theme.SkeletonComposeTheme
import com.addam.skeletoncompose.utils.collectAsMutableState
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by addam on 24/11/2022
 */
@AndroidEntryPoint
class LoginActivity: ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkeletonComposeTheme(){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginField()
                }
            }
        }
    }
}

//If don't want to use Hilt inject, just use viewModel() compose
@Composable
private fun LoginField(loginViewModel: LoginViewModel = viewModel()){
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
    Column(modifier = Modifier.padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        UsernameField(loginViewModel)
        Spacer(modifier = Modifier.padding(top = 8.dp))
        PasswordField(loginViewModel)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        UserDetails(loginViewModel)
        Spacer(modifier = Modifier.padding(top = 16.dp))
        LoginButton(loginViewModel)
    }
}

@Composable
private fun UsernameField(vm: LoginViewModel = viewModel()) {
    val (username, setUsername) = vm.username.collectAsMutableState()

    OutlinedTextField(
        value = username,
        onValueChange = setUsername,
        label = { Text("Username") },
        placeholder = { Text("Please enter your username") },
        modifier = Modifier,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun PasswordField(loginViewModel: LoginViewModel) {
//    val password by remember { mutableStateOf("") }
//    val viewModel: LoginViewModel = remember { LoginViewModel() }
    val (password, setPassword) = loginViewModel.password.collectAsMutableState()

    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = setPassword,
        label = { Text("Password") },
        placeholder = { Text("Please enter your password") },
        modifier = Modifier,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if(passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(
                onClick = {passwordVisible = !passwordVisible}
            ){
                Icon(imageVector  = image, "")
            }
        }
    )
}

@Composable
private fun UserDetails(loginViewModel: LoginViewModel) {
    val username = loginViewModel.username.collectAsMutableState()
    val password = loginViewModel.password.collectAsMutableState()

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row{
            Text(text = "${stringResource(R.string.username_header)}:")
            Text(text = username.value)
        }

        Row{
            Text(text = "${stringResource(R.string.password_header)}:")
            Text(text = password.value)
        }

    }
}

@Composable
private fun LoginButton(viewModel: LoginViewModel){
    val isButtonEnabled = viewModel.isFormValid.collectAsState(false)
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show()
        },
        enabled = isButtonEnabled.value
    ){
        Text("Login")
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFieldPreview(){
    Surface(modifier = Modifier){
        LoginField()
    }
}

@Preview(name = "Login", showBackground = true)
@Composable
private fun LoginPreview(){
    SkeletonComposeTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginField()
        }
    }
}