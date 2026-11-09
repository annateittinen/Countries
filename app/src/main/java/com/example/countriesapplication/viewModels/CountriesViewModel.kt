package com.example.countriesapplication.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapplication.datasource.CountriesRepository
import com.example.countriesapplication.model.CountryDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    val countriesRepository: CountriesRepository,
): ViewModel() {

    private val _countryDataState: MutableStateFlow<CountryDataState> = MutableStateFlow(
        CountryDataState.CountryDataStateSuccess())
    val countryDataState: StateFlow<CountryDataState> = _countryDataState.asStateFlow()

    fun getAll(page: Int = 1) {

        viewModelScope.launch{

            var countryDataState: CountryDataState = countriesRepository.getAll(page = page)

            Log.d(
                "test1",
                "CountriesViewModel: called countriesRepository.getAll: countriesFromRepo size=${countryDataState.countries.size} items=${countryDataState.countries}"
            )

            _countryDataState.update { countryDataState }
        }
    }
}