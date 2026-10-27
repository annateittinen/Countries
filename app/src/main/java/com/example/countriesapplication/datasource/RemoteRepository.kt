package com.example.countriesapplication.datasource

import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.network.CountryApiService
import com.example.countriesapplication.network.ResponseCountries
import com.example.countriesapplication.utils.toCountryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: CountryApiService,
) {
    suspend fun getAll(page: Int = 1): List<CountryModel> {
        val responseCountries: ResponseCountries = apiService.getCountries(page)

        val countries: List<CountryModel> = responseCountries.data.mapNotNull {
            it.toCountryModel()
        }

        return countries
    }
}
