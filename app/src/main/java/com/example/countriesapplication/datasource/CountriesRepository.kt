package com.example.countriesapplication.datasource

import com.example.countriesapplication.model.CountryDataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
    val localRepository: LocalRepository,
    val remoteRepository: RemoteRepository,
) {
    /**
     * Retrieve items from local DB.
     * If there are no items, then fetch from network and save to db.
     *
     */
    suspend fun getAll(dispatcher: CoroutineDispatcher = Dispatchers.IO,
                       page: Int = 1): CountryDataState
        = withContext(dispatcher) {

        // Fetch from local DB.
        val countryDataStateLocalDb = localRepository.getAll()

        if (countryDataStateLocalDb is CountryDataState.CountryDataStateSuccess &&
            countryDataStateLocalDb.countries.isNotEmpty()) {
            return@withContext countryDataStateLocalDb
        } else {

            // Fetch from network.
            val countryDataStateRemote = remoteRepository.getAll(page = page)
            if (countryDataStateRemote is CountryDataState.CountryDataStateSuccess) {
                if (countryDataStateRemote.countries.isNotEmpty()) {
                    localRepository.saveAll(countries = countryDataStateRemote.countries)
                }
            }
            return@withContext countryDataStateRemote
        }
    }
}