package com.mozeago.basicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mozeago.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GreetingMyAppCodelLab1()
                }
            }
        }
    }

    @Composable
    fun OnboardingScreen(onContinuedClicked: () -> Unit) {
        Surface() {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Welcome to The codelab 01")
                Button(
                    modifier = Modifier.padding(vertical = 24.dp),
                    onClick = onContinuedClicked
                ) {
                    Text("Continue")
                }
            }
        }
    }

    @Preview(showBackground = true, widthDp = 320, heightDp = 320)
    @Composable
    fun OnBoardingPreview() {
        BasicsCodelabTheme {
            OnboardingScreen(onContinuedClicked = {})
        }
    }

    @Composable
    fun Greeting(name: String) {
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }
        val extraPadding by animateDpAsState(if (expanded) 48.dp else 0.dp)
        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello,")
                    Text(text = name)
                }
                OutlinedButton(onClick = {
                    expanded = !expanded
                }) {
                    Text(if (expanded) "Show Less" else "Show More")
                }
            }
        }
    }

    @Composable
    fun GreetingMyAppCodelLab1() {
        var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinuedClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }

    @Composable
    fun Greetings(names: List<String> = List(1000) { "$it" }) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

    @Preview(showBackground = true, widthDp = 320)
    @Composable
    fun DefaultPreview() {
        BasicsCodelabTheme {
            GreetingMyAppCodelLab1()
        }
    }

}