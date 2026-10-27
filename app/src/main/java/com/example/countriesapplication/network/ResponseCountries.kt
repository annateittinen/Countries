package com.example.countriesapplication.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseCountries(
    val page: Int = 0,
    @SerialName("per_page")
    val perPage: Int = 0,
    val total: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0,
    val data: List<CountryNetworkData> = emptyList()
)
