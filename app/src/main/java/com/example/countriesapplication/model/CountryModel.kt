package com.example.countriesapplication.model

data class CountryModel(
    val name: String,
    val nativeName: String,
    val currencies: List<String> = emptyList(),
    val callingCodes: List<String> = emptyList(),
    val capital: String,
    val region: String,
    val subregion: String,
    val language: List<String> = emptyList(),
    val population: Long,
) {
}