package com.example.countriesapplication.model

sealed class CountryDataState(val countries: List<CountryModel> = emptyList()) {
    data class CountryDataStateSuccess(val countriesList: List<CountryModel> = emptyList()): CountryDataState(countriesList)

    sealed class CountryDataStateError: CountryDataState() {

        class CountryDataSQLiteAccessPermError : CountryDataStateError()
        class CountryDataSQLiteConstraintError : CountryDataStateError()
        class CountryDataSQLiteDatabaseLockedError : CountryDataStateError()
        class CountryDataSQLiteDatabaseCorruptError : CountryDataStateError()
        class CountryDataSQLiteDiskIOExceptionError : CountryDataStateError()
        class CountryDataSQLiteDoneExceptionError : CountryDataStateError()
        class CountryDataSQLiteExceptionError : CountryDataStateError()

        class CountryDataHttpExceptionError() : CountryDataStateError()
        class CountryDataIOExceptionError() : CountryDataStateError()

        class CountryDataExceptionError(): CountryDataStateError()
    }
}