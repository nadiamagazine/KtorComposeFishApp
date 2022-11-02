package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.service.KtorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesDetailViewModel(
    speciesName: String
) : ViewModel() {

    init {
        getSpeciesDetailedInfo(speciesName)
    }

    private var _liveData = MutableLiveData<List<SpeciesDetailedInfo>>()
    val liveData: LiveData<List<SpeciesDetailedInfo>> = _liveData

    fun getSpeciesDetailedInfo(speciesName: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfSpecies =
                KtorService.create().getSpeciesDetailedInfo(speciesName = speciesName)
            _liveData.postValue(listOfSpecies)
        }
    }

    companion object {
        fun factory(speciesName: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SpeciesDetailViewModel(speciesName)
                }
            }
    }
}
