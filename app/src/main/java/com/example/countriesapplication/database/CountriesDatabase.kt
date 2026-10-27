package com.example.countriesapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 1)
abstract class CountriesDatabase: RoomDatabase() {

    abstract fun getCountryDao() : CountryDao

}
