package com.example.countriesapplication.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.countriesapplication.datasource.CountriesRepository
import com.example.countriesapplication.model.CountryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    val countriesRepository: CountriesRepository,
): ViewModel() {

    private val _countries: MutableStateFlow<List<CountryModel>> = MutableStateFlow(emptyList())
    val countries: StateFlow<List<CountryModel>> = _countries.asStateFlow()

    suspend fun getAll(page: Int = 1) {

        var countriesFromRepo : List<CountryModel> = countriesRepository.getAll(page)

        Log.d("test1", "CountriesViewModel: called countriesRepository.getAll: countriesFromRepo size=${countriesFromRepo.size} items=${countriesFromRepo}")


        _countries.emit(countriesFromRepo)
    }
}