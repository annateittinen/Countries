package com.example.countriesapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.countriesapplication.ui.CountriesHomeScreen
import com.example.countriesapplication.ui.CountriesHomeScreenContent
import com.example.countriesapplication.ui.theme.CountriesApplicationTheme
import com.example.countriesapplication.utils.TestData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // Get the CountriesViewModel tied to the Activity lifecycle
    //private val countriesViewModel: CountriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )

                    CountriesHomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview //(showBackground = true)
@PreviewLightDark
@Composable
fun GreetingPreview() {
    CountriesApplicationTheme {
        Greeting("Android")
    }
}

//@Preview //(showBackground = true)
@PreviewLightDark
@Composable
fun CountriesHomeScreenPreview() {
    CountriesApplicationTheme {
        CountriesHomeScreenContent(
            countries = TestData.countries,)
    }
}
