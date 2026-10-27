package com.example.countriesapplication.utils

import com.example.countriesapplication.model.CountryModel

object TestData {

    val countries: List<CountryModel> = listOf(
        CountryModel(
            name = "Afghanistan",
            nativeName = "افغانستان",
            currencies = listOf("AFN"),
            callingCodes = listOf("93"),
            capital = "Kabul",
            region = "Asia",
            subregion = "Southern Asia",
            language = listOf("Pashto", "Dari"),
            population = 26023100,
        ),

        CountryModel(
            name = "Albania",
            nativeName = "Shqipëria",
            currencies = listOf("ALL"),
            callingCodes = listOf("355"),
            capital = "Tirana",
            region = "Europe",
            subregion = "Southern Europe",
            language = listOf("Albanian"),
            population = 2893005,
        ),

        CountryModel(
            name = "Algeria",
            nativeName = "الجزائر",
            currencies = listOf("DZD"),
            callingCodes = listOf("213"),
            capital = "Algiers",
            region = "Africa",
            subregion = "Northern Africa",
            language = listOf("Arabic"),
            population = 2893005,
        ),

        CountryModel(
            name = "American Samoa",
            nativeName = "American Samoa",
            currencies = listOf("USD"),
            callingCodes = listOf("1684"),
            capital = "Pago Pago",
            region = "Oceania",
            subregion = "Polynesia",
            language = listOf("English", "Samoan"),
            population = 55519,
        ),

        CountryModel(
            name = "Andorra",
            nativeName = "Andorra",
            currencies = listOf("EUR"),
            callingCodes = listOf("376"),
            capital = "Andorra la Vella",
            region = "Europe",
            subregion = "Southern Europe",
            language = listOf("Catalan"),
            population = 76949,
        ),

        CountryModel(
            name = "Angola",
            nativeName = "Angola",
            currencies = listOf("AOA"),
            callingCodes = listOf("244"),
            capital = "Luanda",
            region = "Africa",
            subregion = "Middle Africa",
            language = listOf("Portuguese"),
            population = 24383301,
        ),

        CountryModel(
            name = "Anguilla",
            nativeName = "Anguilla",
            currencies = listOf("XCD"),
            callingCodes = listOf("1264"),
            capital = "The Valley",
            region = "Americas",
            subregion = "Caribbean",
            language = listOf("English"),
            population = 24383301,
        ),

        CountryModel(
            name = "Antarctica",
            nativeName = "Antarctica",
            currencies = listOf("AUD", "GBP"),
            callingCodes = listOf(""),
            capital = "",
            region = "Polar",
            subregion = "",
            language = listOf("English"),
            population = 1000,
        ),
    )

}