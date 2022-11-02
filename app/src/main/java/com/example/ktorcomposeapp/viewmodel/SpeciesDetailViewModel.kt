package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.service.KtorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//add function to clear details from previous fish
class SpeciesDetailViewModel(
    val speciesName: String
) : ViewModel() {

    init {
        getSpeciesDetailedInfo(speciesName)
    }

    var success = true

    private var _liveData = MutableLiveData<List<SpeciesDetailedInfo>>()
    val liveData: LiveData<List<SpeciesDetailedInfo>> = _liveData

        fun getSpeciesDetailedInfo(speciesName: String) =  viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val listOfSpecies =
                    KtorService.create().getSpeciesDetailedInfo(speciesName = speciesName)
                _liveData.postValue(listOfSpecies)
            }
        }
}
