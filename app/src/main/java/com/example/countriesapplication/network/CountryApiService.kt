package com.example.countriesapplication.network

import retrofit2.http.GET
import retrofit2.http.Query

interface CountryApiService {
    @GET("countries")
    suspend fun getCountries(
        @Query("page") page: Int = 1
    ): ResponseCountries
}
