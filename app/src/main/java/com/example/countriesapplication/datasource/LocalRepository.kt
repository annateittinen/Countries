package com.example.countriesapplication.datasource

import com.example.countriesapplication.database.CountryDao
import com.example.countriesapplication.database.CountryEntity
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.utils.toCountryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(val countryDao: CountryDao) {

    suspend fun getAll(): List<CountryModel> {

        val listCountryEntities = countryDao.getAll()
        return listCountryEntities.mapNotNull{
            it.toCountryModel()
        }
    }

    suspend fun saveAll(entities: List<CountryEntity>) {
        countryDao.insertAll(entities)
    }

}