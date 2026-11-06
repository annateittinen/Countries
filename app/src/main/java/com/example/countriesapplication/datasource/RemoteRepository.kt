package com.example.countriesapplication.datasource

import android.util.Log
import com.example.countriesapplication.model.CountryDataState
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.network.CountryApiService
import com.example.countriesapplication.utils.toCountryModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: CountryApiService,
) {
    suspend fun getAll(page: Int = 1): CountryDataState {

        var countryDataStateSuccess: CountryDataState.CountryDataStateSuccess? = null
        var countryRemoteDataRetrieveError: CountryDataState.CountryDataStateError? = null

        try {

            // fetch from network
            var responseCountries = apiService.getCountries(page)

            //var responseCountries: ResponseCountries = apiService.getCountries(page)
            val countries: List<CountryModel> = responseCountries.data.mapNotNull {
                it.toCountryModel()
            }
            countryDataStateSuccess = CountryDataState.CountryDataStateSuccess(countries)

            Log.d("test1", "RemoteRepository: responseCountries= $responseCountries")
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    countryRemoteDataRetrieveError =
                        CountryDataState.CountryDataStateError.CountryDataHttpExceptionError()
                }

                is IOException -> {
                    countryRemoteDataRetrieveError =
                        CountryDataState.CountryDataStateError.CountryDataIOExceptionError()
                }

                else -> {
                    countryRemoteDataRetrieveError =
                        CountryDataState.CountryDataStateError.CountryDataExceptionError()
                }
            }
        }

        if (countryDataStateSuccess != null)
            return countryDataStateSuccess

        Log.d("test1", "RemoteRepository: error= ${countryRemoteDataRetrieveError!!::class.simpleName}")
        return countryRemoteDataRetrieveError!!
    }
}
