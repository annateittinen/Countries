package com.example.countriesapplication.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.countriesapplication.R
import com.example.countriesapplication.model.CountryDataState
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.viewModels.CountriesViewModel

@Composable
fun CountriesHomeScreen(modifier: Modifier = Modifier,
                        countriesViewModel: CountriesViewModel = hiltViewModel()
) {
    val countryDataState =
        countriesViewModel.countryDataState.collectAsStateWithLifecycle(
            CountryDataState.CountryDataStateSuccess())

    LaunchedEffect(Unit) {
        Log.d("test1", "LaunchEffect")
        countriesViewModel.getAll()
    }

    val onclick: (CountryModel) -> Unit = {
        Log.d("test1", "clicked country: $it")
    }

    Log.d("test1", "CountriesHomeScreen: countries size=${countryDataState.value.countries.size} items=${countryDataState.value.countries}")

    CountriesHomeScreenContent(
        countryDataState.value.countries,
        modifier,
        onclick)
}

@Composable
fun CountriesHomeScreenContent(
    countries: List<CountryModel> = emptyList(),
    modifier: Modifier = Modifier,
    onclick: (CountryModel) -> Unit = {}
) {

    Log.d("test1", "CountriesHomeScreenContent: countries size=${countries.size} items=${countries}")

    Column(modifier = modifier
        .background(color = Color.LightGray)
        //.background(color = Color(156, 198, 235, 1)) //shade of blue
        //.background(color = Color(80, 174, 232, 1)) //shade of blue
        //.background(color = MaterialTheme.colorScheme.onPrimary)
        .padding(10.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

        Text(
            modifier = modifier.width(300.dp),
            text = "Countries (${countries.size})",
            fontSize = 30.sp
        )

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(countries) {
                key(it.name) {
                    Column(modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable{
                            onclick(it) },
                        verticalArrangement = Arrangement.spacedBy(5.dp),) {

                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(text = it.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxHeight())
                            Spacer(modifier = Modifier.size(5.dp))

                            // coil is backed by Kotlin coroutines to load image away from main thread
                            AsyncImage(
                                model= "https://flagsapi.com/${it.alpha2Code}/flat/64.png",
                                contentDescription = "flag image for ${it.name}",
                                alignment = Alignment.CenterStart,
                                modifier = Modifier.fillMaxSize()
                                    .weight(1f),
                                error = painterResource(R.drawable.ic_launcher_background),
                                fallback = painterResource(R.drawable.ic_launcher_background)
                            )
                        }

                        CountryDataField(field = "population", value = it.population.toString())

                        CountryDataField(field = "region", value = it.region)

                        CountryDataField(field = "capital", value = it.capital)

                        CountryDataField(field = "language", value = it.language.joinToString(", "))

                        CountryDataField(field = "subregion", value = it.subregion)

                        CountryDataField(field = "callingCodes", value = it.callingCodes.joinToString(", "))

                        CountryDataField(field = "currencies", value = it.currencies.joinToString(", "))

                        CountryDataField(field = "nativeName", value = it.nativeName)
                    }
                }
            }
        }
    }
}

@Composable
private fun CountryDataField(modifier: Modifier = Modifier,
                             field: String,
                             value: String) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "$field:",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,)

        Text(text = "$value",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,)
    }

}
