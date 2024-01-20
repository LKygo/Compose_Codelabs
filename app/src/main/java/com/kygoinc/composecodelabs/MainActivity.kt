package com.kygoinc.composecodelabs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kygoinc.composecodelabs.ui.theme.ComposeCodelabsTheme
import java.util.Collections.list

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCodelabsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }


    if (shouldShowOnboarding) {
        OnboardingScreen(isContinueClicked = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}


@Composable
fun Greetings(names: List<String> = List(1000 ) { "Android #$it" }.shuffled()){
    ComposeCodelabsTheme {
        Column {
            Spacer(modifier = Modifier.padding(24.dp))

            LazyColumn {
                items(names){name ->
                    Greeting(name)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween(durationMillis = 1500)
    )

    Column {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello,")
                    Text(text = name)

                }


                FilledTonalButton(
                    onClick = { expanded = !expanded}, modifier = Modifier
                ) {
                    Text(if (expanded) "Show less" else "Show more")
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(isContinueClicked: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(text = "Welcome to Jetpack Compose!")


        Button(
            onClick = {
                isContinueClicked()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Continue")
        }

    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Preview(showBackground = true, widthDp = 320, heightDp = 640, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposeCodelabsTheme {
        OnboardingScreen(isContinueClicked = {})
    }
}


@Preview(showBackground = true, widthDp = 320)
@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    ComposeCodelabsTheme {
        Greetings()
    }
}