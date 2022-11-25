package com.addam.skeletoncompose.features

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.addam.skeletoncompose.R
import com.addam.skeletoncompose.core.Router
import com.addam.skeletoncompose.ui.theme.SkeletonComposeTheme

/**
 * Created by addam on 24/11/2022
 */
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkeletonComposeTheme(){
                BodyContent()
            }
        }
    }
}

@Composable
private fun BodyContent(){
    Column(modifier = Modifier.fillMaxSize()){
        LoginButton()
        Spacer(Modifier.padding(top = 8.dp, bottom = 8.dp))
        BasicSampleButton()
    }
}

@Composable
private fun LoginButton(){
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(
                context, Router.getClass(Router.Destination.LOGIN)
            ))
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){ Text(stringResource(R.string.login)) }
}

@Composable
private fun BasicSampleButton(){
    val context = LocalContext.current
    Button(
        onClick = {
            context.startActivity(Intent(
                context, Router.getClass(Router.Destination.SAMPLE)
            ))
        },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ){ Text(stringResource(R.string.BasicSample)) }
}

@Preview(showBackground = true)
@Composable
private fun BodyContentPreview(){
    SkeletonComposeTheme(){
        BodyContent()
    }
}