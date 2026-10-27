package com.example.countriesapplication.datasource

import android.util.Log
import com.example.countriesapplication.database.CountryEntity
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.utils.TestData
import com.example.countriesapplication.utils.toCountryEntity
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
    suspend fun getAll(page: Int = 1): List<CountryModel> {

        // Retrieve items from local DB.
        var countries: List<CountryModel> = localRepository.getAll()
        Log.d("test1", "CountriesRepository: localdb: countries size=${countries.size} items=${countries}")

        if (countries.isEmpty()) {

            // Fetch from network.
            countries = remoteRepository.getAll(page = page)
            Log.d("test1", "CountriesRepository: called remoteRepository.getAll: countries size=${countries.size} items=${countries}")

            // save to local db
            if (countries.isNotEmpty()) {
                val entities: List<CountryEntity> =
                    countries.mapNotNull {
                        it.toCountryEntity()
                    }
                localRepository.saveAll(entities)
                Log.d("test1", "CountriesRepository: saved to db")
            }
        }

        Log.d("test1", "CountriesRepository: returning")
        return countries
    }
}