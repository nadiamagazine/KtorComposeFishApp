package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorcomposeapp.SpeciesName
import com.example.ktorcomposeapp.getListOfSpeciesNames
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesViewModel : ViewModel() {

    private var _liveData = MutableLiveData<List<SpeciesName>>()
    val liveData: LiveData<List<SpeciesName>> = _liveData

    fun getSpeciesName() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfSpecies = getListOfSpeciesNames()
            _liveData.postValue(listOfSpecies.listOfFish)
        }
    }
}
