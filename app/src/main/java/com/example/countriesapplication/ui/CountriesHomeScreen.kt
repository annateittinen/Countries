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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.countriesapplication.R
import com.example.countriesapplication.model.CountryDataState
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.ui.theme.CountriesApplicationTheme
import com.example.countriesapplication.viewModels.CountriesViewModel

object CountryAppTitleDefaults {

    @Composable
    fun styleTitle() : TextStyle = CountriesApplicationTheme.typography.mainTitle

    @Composable
    fun colorTitle(): Color = CountriesApplicationTheme.colors.textPrimary
}

object CountryBoxDefaults {

    @Immutable
    data class BoxColors(
        val colorBoxBackground: Color,
        val colorBoxCountryNameLabel: Color,
        val colorBoxCountryDataFieldLabel: Color,
        val colorBoxCountryDataFieldValue: Color,
    )

    @Immutable
    data class BoxTypograhy(
        val styleBoxCountryNameLabel: TextStyle,
        val styleBoxFieldLabel: TextStyle,
        val styleBoxFieldValue: TextStyle,
    )

    @Composable
    fun colors(
        colorBoxBackground: Color = CountriesApplicationTheme.colors.cardContainer,
        colorBoxCountryNameLabel: Color = CountriesApplicationTheme.colors.textPrimary,
        colorBoxCountryDataFieldLabel: Color = CountriesApplicationTheme.colors.textPrimary,
        colorBoxCountryDataFieldValue: Color = CountriesApplicationTheme.colors.textPrimary,
    ) : BoxColors = BoxColors(
        colorBoxBackground,
        colorBoxCountryNameLabel,
        colorBoxCountryDataFieldLabel,
        colorBoxCountryDataFieldValue,
    )

    @Composable
    fun typography(
        styleBoxCountryNameLabel : TextStyle = CountriesApplicationTheme.typography.countryNameTitle,
        styleBoxFieldLabel: TextStyle = CountriesApplicationTheme.typography.countryFieldLabel,
        styleBoxFieldValue: TextStyle = CountriesApplicationTheme.typography.countryFieldValue,
    ) : BoxTypograhy = BoxTypograhy (
        styleBoxCountryNameLabel,
        styleBoxFieldLabel,
        styleBoxFieldValue,
    )

}

@Composable
fun CountriesHomeScreen(modifier: Modifier = Modifier,
                        countriesViewModel: CountriesViewModel = hiltViewModel()
) {
    val countryDataState =
        countriesViewModel.countryDataState.collectAsStateWithLifecycle(
            CountryDataState.CountryDataStateSuccess())

    val onclick: (CountryModel) -> Unit = {
        Log.d("test1", "clicked country: $it")
    }

    Log.d("test1", "CountriesHomeScreen: countries size=${countryDataState.value.countries.size} items=${countryDataState.value.countries}")

    CountriesHomeScreenContent(
        countryDataState.value.countries,
        modifier,
        boxColors = CountryBoxDefaults.colors(),
        boxTypograhy = CountryBoxDefaults.typography(),
        onclick = onclick)
}



@Composable
fun CountriesHomeScreenContent(
    countries: List<CountryModel> = emptyList(),
    modifier: Modifier = Modifier,
    onclick: (CountryModel) -> Unit = {},
    boxColors: CountryBoxDefaults.BoxColors = CountryBoxDefaults.colors(),
    boxTypograhy: CountryBoxDefaults.BoxTypograhy = CountryBoxDefaults.typography(),
) {

    Log.d("test1", "CountriesHomeScreenContent: countries size=${countries.size} items=${countries}")

    Column(modifier = modifier
        .background(color = CountriesApplicationTheme.colors.background)
        .padding(10.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

        Text(
            modifier = modifier.width(300.dp),
            text = "Countries (${countries.size})",
            style = CountryAppTitleDefaults.styleTitle(),
            color = CountryAppTitleDefaults.colorTitle(),
        )

        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(countries) {
                key(it.name) {
                    CountryBox(modifier = modifier,
                        onclick = onclick,
                        boxColors = boxColors,
                        boxTypograhy = boxTypograhy,
                        countryModel = it)
                }
            }
        }
    }
}

@Composable
private fun CountryBox(
    modifier: Modifier = Modifier,
    onclick: (CountryModel) -> Unit = {},
    boxColors: CountryBoxDefaults.BoxColors = CountryBoxDefaults.colors(),
    boxTypograhy: CountryBoxDefaults.BoxTypograhy = CountryBoxDefaults.typography(),
    countryModel: CountryModel,
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = boxColors.colorBoxBackground)
        .clickable {
            onclick(countryModel)
        },
        verticalArrangement = Arrangement.spacedBy(5.dp),) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = countryModel.name,
                style = boxTypograhy.styleBoxCountryNameLabel,
                color = boxColors.colorBoxCountryNameLabel,
                modifier = Modifier.fillMaxHeight())
            Spacer(modifier = Modifier.size(5.dp))

            // coil is backed by Kotlin coroutines to load image away from main thread
            AsyncImage(
                model= "https://flagsapi.com/${countryModel.alpha2Code}/flat/64.png",
                contentDescription = "flag image for ${countryModel.name}",
                alignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                error = painterResource(R.drawable.ic_launcher_background),
                fallback = painterResource(R.drawable.ic_launcher_background)
            )
        }

        CountryDataField(field = "population", value = countryModel.population.toString())

        CountryDataField(field = "region", value = countryModel.region)

        CountryDataField(field = "capital", value = countryModel.capital)

        CountryDataField(field = "language", value = countryModel.language.joinToString(", "))

        CountryDataField(field = "subregion", value = countryModel.subregion)

        CountryDataField(field = "callingCodes", value = countryModel.callingCodes.joinToString(", "))

        CountryDataField(field = "currencies", value = countryModel.currencies.joinToString(", "))

        CountryDataField(field = "nativeName", value = countryModel.nativeName)
    }
}


@Composable
private fun CountryDataField(modifier: Modifier = Modifier,
                             boxColors: CountryBoxDefaults.BoxColors = CountryBoxDefaults.colors(),
                             boxTypograhy: CountryBoxDefaults.BoxTypograhy = CountryBoxDefaults.typography(),
                             field: String,
                             value: String) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = "$field:",
            style = boxTypograhy.styleBoxFieldLabel,
            color = boxColors.colorBoxCountryDataFieldLabel,
            )

        Text(text = "$value",
            style = boxTypograhy.styleBoxFieldValue,
            color = boxColors.colorBoxCountryDataFieldValue,
            )
    }

}
