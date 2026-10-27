package com.example.countriesapplication.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.viewModels.CountriesViewModel

@Composable
fun CountriesHomeScreen(modifier: Modifier = Modifier,
                        countriesViewModel: CountriesViewModel = hiltViewModel()
) {

    val countries =
        countriesViewModel.countries.collectAsStateWithLifecycle(emptyList())

    LaunchedEffect(Unit) {
        Log.d("test1", "LaunchEffect")
        countriesViewModel.getAll()
    }

    Log.d("test1", "CountriesHomeScreen: countries size=${countries.value.size} items=${countries.value}")

    CountriesHomeScreenContent(
        countries.value,
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
        .fillMaxSize()
        ) {

        Text(
            modifier = modifier.background(color=Color.LightGray),
            text = "Countries",
            fontSize = 40.sp
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
                        //.size(width = 500.dp, height = 300.dp),
                        ,verticalArrangement = Arrangement.spacedBy(5.dp)) {

                        Text(text = it.name, fontSize = 30.sp)
                        Text(text = it.population.toString(), fontSize = 20.sp)
                        Text(text = it.region, fontSize = 20.sp)
                        Text(text = it.capital, fontSize = 20.sp)

                        Text(text = it.language.joinToString(", "), fontSize = 20.sp)

                        Text(text = it.subregion, fontSize = 20.sp)

                        Text(text = it.callingCodes.joinToString(", "), fontSize = 20.sp)

                        Text(text = it.currencies.joinToString(","), fontSize = 20.sp)
                        Text(text = it.nativeName, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}
