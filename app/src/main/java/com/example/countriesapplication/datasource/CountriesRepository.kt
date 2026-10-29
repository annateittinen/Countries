package com.example.countriesapplication.datasource

import com.example.countriesapplication.model.CountryDataState
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
    suspend fun getAll(page: Int = 1): CountryDataState {

        var countryDataStateLocalDb: CountryDataState ? = null
        var countryDataStateRemote: CountryDataState ?= null

        // Fetch from local DB.
        countryDataStateLocalDb = localRepository.getAll()

        if (countryDataStateLocalDb is CountryDataState.CountryDataStateSuccess &&
            countryDataStateLocalDb.countries.isNotEmpty()) {
            return countryDataStateLocalDb
        } else {

            // Fetch from network.
            countryDataStateRemote = remoteRepository.getAll(page)
            if (countryDataStateRemote is CountryDataState.CountryDataStateSuccess) {
                if (countryDataStateRemote.countries.isNotEmpty()) {
                    localRepository.saveAll(countryDataStateRemote.countries)
                }
            }
            return countryDataStateRemote!!
        }
    }
}