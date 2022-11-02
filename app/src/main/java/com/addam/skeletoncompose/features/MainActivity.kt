package com.addam.skeletoncompose.features

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.addam.skeletoncompose.models.Message
import com.addam.skeletoncompose.ui.theme.SkeletonComposeTheme
import com.addam.skeletoncompose.R

/**For preview widget need to see [PreviewMessageCard]*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MessageCard("Android")
            PreviewMessageCard()
        }
    }
}

//Preview annotation is to preview the widget to the Design panel
//Can add multiple Preview annotation to view different features of one function
@Preview(name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true)
@Preview(name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true)
@Composable
fun PreviewMessageCard(){
//    MessageCard("Android")
//    MessageCardModel(Message("Addam", "Compose"))
    SkeletonComposeTheme(){
        Surface(modifier = Modifier.fillMaxSize()){
            MessageCardWithImage(Message("Addam", "Coder"))
        }
    }
}

@Composable
fun MessageCard(name: String){
    Text("Hello $name")
}

@Composable
fun MessageCardModel(msg: Message){
    Column{
        Text(msg.author)
        Text(msg.body)
    }
}

//Modifiers are optional param that has all the width, height and other widget param like in XML
@Composable
fun MessageCardWithImage(msg: Message){
    Row(modifier = Modifier.padding(all = 8.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_alarm_on),
            contentDescription = "Something",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        // Add a horizontal spacing between image and column
        Spacer(modifier = Modifier.width(8.dp))

        Column{
            Text(text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            //Add a vertical spacer between columns
            Spacer(modifier = Modifier.height(8.dp))

            //Surface is a widget wrapper that allows further customization, like FrameLayout
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp){
                Text(text = msg.body,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    SkeletonComposeTheme {
//        Greeting("Android")
//    }
//}