package com.example.countriesapplication.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    Log.d("test1", "CountriesHomeScreen: countries size=${countryDataState.value.countries.size} items=${countryDataState.value.countries}")

    CountriesHomeScreenContent(
        countryDataState.value.countries,
        modifier)
}

@Composable
fun CountriesHomeScreenContent(
    countries: List<CountryModel> = emptyList(),
    modifier: Modifier = Modifier,
) {

    Log.d("test1", "CountriesHomeScreenContent: countries size=${countries.size} items=${countries}")

    Column(modifier = modifier
        .padding(30.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

        Text(
            modifier = modifier.background(color=Color.LightGray)
                .width(300.dp),
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
                        .background(color=Color.LightGray)
                        .width(300.dp)
                        //.size(width = 300.dp, height = 300.dp)
                        .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(5.dp)) {

                        Text(text = it.name, fontSize = 20.sp)
                        Text(text = "population: ${it.population}", fontSize = 15.sp)
                        Text(text = "region: ${it.region}", fontSize = 15.sp)
                        Text(text = "capital: ${it.capital}", fontSize = 15.sp)
                        Text(text = "language: ${it.language.joinToString(", ")}", fontSize = 15.sp)
                        Text(text = "subregion: ${it.subregion}", fontSize = 15.sp)
                        Text(text = "callingCodes: ${it.callingCodes.joinToString(", ")}", fontSize = 15.sp)
                        Text(text = "currencies: ${it.currencies.joinToString(", ")}", fontSize = 15.sp)
                        Text(text ="nativeName: ${it.nativeName}", fontSize = 15.sp)
                    }
                }
            }
        }
    }
}
