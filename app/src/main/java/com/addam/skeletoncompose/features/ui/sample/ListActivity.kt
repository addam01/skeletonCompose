package com.addam.skeletoncompose.features.ui.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.addam.skeletoncompose.R
import com.addam.skeletoncompose.features.ui.theme.SkeletonComposeTheme
import com.addam.skeletoncompose.models.Message
import com.addam.skeletoncompose.models.SampleData

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkeletonComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Conversation(SampleData.conversationSample)
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    //LazyColumn is the default adapter, put the list of data into it without the need to manually
    //create an adapter.
    // MessageCardWithImage is the widget function that we made in [MainActivity], like XML
    LazyColumn{
        messages.map { item { MessageCardWithAnimation(it) } }
    }
}

/** We can also add an animation, customize the [MessageCardWithImage]**/
@Composable
fun MessageCardWithAnimation(msg: Message){
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

        /**We Keep track if the message is expanded or not in this variable, use remember**/
        var isExpanded by remember { mutableStateOf(false) }

        /**We can also add a variable to swap colors**/
        val surfaceColor by animateColorAsState(
            if(isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )

        Column(
            /**This enables the column to be clickable with a defined function**/
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        ){
            Text(text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            //Add a vertical spacer between columns
            Spacer(modifier = Modifier.height(8.dp))

            //Surface is a widget wrapper that allows further customization, like FrameLayout
            Surface(shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
                /**Surface color based on the [surfaceColor]**/
                color = surfaceColor,
                modifier = Modifier.animateContentSize().padding(1.dp)
            ){
                Text(text = msg.body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(all = 4.dp),
                    /**If message is Expanded, we display all. Else display 1st line**/
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}