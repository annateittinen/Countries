package com.example.countriesapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey val name: String,
    @ColumnInfo val nativeName: String,

    // save currencies as String in db;
    //  --> converted to String from Kotlin List<String> in CountryNetworkData and CountryModel
    //@ColumnInfo val currencies: List<String>,
    @ColumnInfo val currencies: String,

    // save currencies as String in db;
    //  --> converted to String from Kotlin List<String> in CountryNetworkData and CountryModel
    //@ColumnInfo val callingCodes: List<String>,
    @ColumnInfo val callingCodes: String,

    @ColumnInfo val capital: String,
    @ColumnInfo val region: String,
    @ColumnInfo val subregion: String,

    // save currencies as String in db;
    //  --> converted to String from Kotlin List<String> in CountryNetworkData and CountryModel
    //@ColumnInfo val language: List<String>,
    @ColumnInfo val language: String,

    @ColumnInfo val population: Long,
    ) {
}