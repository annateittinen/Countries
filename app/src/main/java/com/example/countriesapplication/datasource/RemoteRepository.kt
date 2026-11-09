package com.example.countriesapplication.datasource

import android.util.Log
import com.example.countriesapplication.model.CountryDataState
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.network.CountryApiService
import com.example.countriesapplication.utils.toCountryModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val apiService: CountryApiService,
) {
    suspend fun getAll(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                       page: Int = 1): CountryDataState
        = withContext(dispatcher) {

        try {

            // fetch from network
            var responseCountries = apiService.getCountries(page)

            val countries: List<CountryModel> = responseCountries.data.mapNotNull {
                it.toCountryModel()
            }
            var countryDataStateSuccess = CountryDataState.CountryDataStateSuccess(countries)

            Log.d("test1", "RemoteRepository: responseCountries= $responseCountries")

            return@withContext countryDataStateSuccess
        } catch (e: Exception) {
            Log.d("test1", "RemoteRepository: error= ${e::class.simpleName}")

            when (e) {
                is HttpException -> {
                    return@withContext CountryDataState.CountryDataStateError.CountryDataHttpExceptionError()
                }

                is IOException -> {
                    return@withContext CountryDataState.CountryDataStateError.CountryDataIOExceptionError()
                }

                else -> {
                    return@withContext CountryDataState.CountryDataStateError.CountryDataExceptionError()
                }
            }
        }
    }
}
