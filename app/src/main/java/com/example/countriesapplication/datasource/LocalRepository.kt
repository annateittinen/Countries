package com.example.countriesapplication.datasource

import android.database.sqlite.SQLiteAccessPermException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabaseCorruptException
import android.database.sqlite.SQLiteDatabaseLockedException
import android.database.sqlite.SQLiteDiskIOException
import android.database.sqlite.SQLiteDoneException
import android.util.Log
import androidx.sqlite.SQLiteException
import com.example.countriesapplication.database.CountryDao
import com.example.countriesapplication.database.CountryEntity
import com.example.countriesapplication.model.CountryDataState
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.utils.toCountryEntity
import com.example.countriesapplication.utils.toCountryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(val countryDao: CountryDao) {

    suspend fun getAll(): CountryDataState {

        var countryDataStateSuccess: CountryDataState.CountryDataStateSuccess? = null
        var countryDataStateError: CountryDataState.CountryDataStateError ? = null

        try {
            val listCountryEntities: List<CountryEntity> = countryDao.getAll()
            val countries: List<CountryModel> = listCountryEntities.mapNotNull {
                it.toCountryModel()
            }
            countryDataStateSuccess = CountryDataState.CountryDataStateSuccess(countries)

            Log.d("test1", "LocalRepository: from db: size=${countryDataStateSuccess.countries.size} ; items=${countryDataStateSuccess.countries}")
        } catch (e: SQLiteException) {
            countryDataStateError = getErrorState(e)

            Log.d("test1", "LocalRepository: SQLiteException")
        }

        if (countryDataStateSuccess != null)
            return countryDataStateSuccess

        return countryDataStateError!!
    }


    suspend fun saveAll(countries: List<CountryModel>) : CountryDataState {

        var countryLocalDataError: CountryDataState.CountryDataStateError ? = null

        val entities: List<CountryEntity> =
            countries.mapNotNull {
                it.toCountryEntity()
            }

        try {
            countryDao.insertAll(entities)
            Log.d("test1", "LocalRepository: inserted to db: $entities")
        } catch (e: SQLiteException) {
            countryLocalDataError = getErrorState(e)
            Log.d("test1", "LocalRepository: insert error: ${countryLocalDataError::class.simpleName}")
        }

        if (countryLocalDataError != null)
            return countryLocalDataError

        return CountryDataState.CountryDataStateSuccess(countries)
    }

    private fun getErrorState(e: SQLiteException) : CountryDataState.CountryDataStateError {

        var countryDataStateError: CountryDataState.CountryDataStateError ? = null

        when (e) {
            is SQLiteAccessPermException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteAccessPermError()
            }
            is SQLiteConstraintException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteConstraintError()
            }
            is SQLiteDatabaseLockedException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteDatabaseLockedError()
            }
            is SQLiteDatabaseCorruptException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteDatabaseCorruptError()
            }
            is SQLiteDiskIOException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteDiskIOExceptionError()
            }
            is SQLiteDoneException -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteDoneExceptionError()
            }
            else -> {
                countryDataStateError = CountryDataState.CountryDataStateError.CountryDataSQLiteExceptionError()
            }
        }

        return countryDataStateError
    }
}