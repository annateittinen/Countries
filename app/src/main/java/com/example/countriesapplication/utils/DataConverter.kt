package com.example.countriesapplication.utils

import com.example.countriesapplication.database.CountryEntity
import com.example.countriesapplication.model.CountryModel
import com.example.countriesapplication.network.CountryNetworkData


fun stringToList(str: String) : List<String> =
    str.split(',').map { it.trim() }

fun listToString(items: List<String>) : String =
    items.joinToString(",")

fun CountryNetworkData.toCountryModel() : CountryModel =
    CountryModel(
        name,
        nativeName,
        currencies,
        callingCodes,
        capital,
        region,
        subregion,
        language,
        population,
        alpha2Code,
    )

fun CountryEntity.toCountryModel() : CountryModel =
    CountryModel(
        name,
        nativeName,
        currencies = stringToList(currencies),
        callingCodes = stringToList(callingCodes),
        capital,
        region,
        subregion,
        language = stringToList(language),
        population,
        alpha2Code,
    )

fun CountryModel.toCountryEntity() : CountryEntity =
    CountryEntity(
        name,
        nativeName,
        currencies = listToString(currencies),
        callingCodes = listToString(callingCodes),
        capital,
        region,
        subregion,
        language = listToString(language),
        population,
        alpha2Code,
    )
