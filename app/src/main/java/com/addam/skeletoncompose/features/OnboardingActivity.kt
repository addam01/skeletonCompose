package com.addam.skeletoncompose.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.addam.skeletoncompose.features.ui.theme.SkeletonComposeTheme

/** The state that is read or modified by multiple functions
 * should live in a common ancestor—this process is called state hoisting
*/
class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingPreview()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    //This state should be hoisted
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

    SkeletonComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background){
            if(shouldShowOnBoarding){
                //When clicked will update shouldShorOnboarding to false which hides the Screen
                OnboardingScreen(onContinueClicked = {shouldShowOnBoarding = false })
            } else {
//                GreetingLists()
                GreetingLazyList()
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit,
                     modifier: Modifier = Modifier){

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to Basic in Compose!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ){
            Text("Continue")
        }
    }
}

@Composable
fun GreetingLists(modifier: Modifier = Modifier,
                  names: List<String> = listOf("World", "Compose"))
{
    Column(modifier = modifier.padding(vertical = 4.dp)){
        for (name in names){
            Greeting(name)
        }
    }
}

/**LazyColumn doesn't recycle its children like RecyclerView.
 * It emits new Composables as you scroll through it and is still performant,
 * as emitting Composables is relatively cheap compared to instantiating Android Views
 */
@Composable
fun GreetingLazyList(modifier: Modifier = Modifier,
                     names: List<String> = List(100){"$it"})
{
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
        items(items = names){ name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String){
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(
            modifier = Modifier.padding(24.dp)
        ){
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)){

                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = {expanded.value = !expanded.value}){
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}