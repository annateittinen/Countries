package com.example.countriesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.countriesapplication.database.CountriesDatabase
import com.example.countriesapplication.database.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCountriesDatabase(
        @ApplicationContext context: Context,
    ): CountriesDatabase {
        return Room.databaseBuilder(
            context,
            CountriesDatabase::class.java,
            "countries_database"
        )
            // App development is in early stage or not in production environment
            // and do not need to preserve existing data on the device.
            // Force Room db to drop and recreate the tables
            // when a version change is detected.
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryDao(database: CountriesDatabase): CountryDao {
        return database.getCountryDao()
    }
}
