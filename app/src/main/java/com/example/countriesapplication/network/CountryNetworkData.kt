package com.example.countriesapplication.network

import kotlinx.serialization.Serializable

@Serializable
data class CountryNetworkData(
    val name: String,
    val nativeName: String,
    val currencies: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val region: String,
    val subregion: String,
    val language: List<String>,
    val population: Long,
) {
}