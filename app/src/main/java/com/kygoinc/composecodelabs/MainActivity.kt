package com.kygoinc.composecodelabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kygoinc.composecodelabs.ui.theme.ComposeCodelabsTheme

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
fun MyApp(names: List<String> = listOf("Android", "Compose")) {
    ComposeCodelabsTheme {
        Column {
            Spacer(modifier = Modifier.padding(24.dp))

            for (name in names) {
                Greeting(name)
            }

        }

    }
}


@Composable
fun Greeting(name: String) {
    var expanded = remember { mutableStateOf(false) }
    var extraPadding = if (expanded.value) 48.dp else 0.dp

    Column {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello,")
                    Text(text = name)

                }

                FilledTonalButton(
                    onClick = { expanded.value = !expanded.value }, modifier = Modifier
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeCodelabsTheme {
        MyApp()
    }
}