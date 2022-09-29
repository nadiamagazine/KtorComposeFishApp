package com.example.ktorcomposeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorcomposeapp.model.SpeciesResponse
import com.example.ktorcomposeapp.service.KtorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesViewModel : ViewModel() {

    private var _liveData = MutableLiveData<List<SpeciesResponse>>()
    val liveData: LiveData<List<SpeciesResponse>> = _liveData

    fun getSpeciesName() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfSpecies = KtorService.create().getListOfSpeciesNames()
            _liveData.postValue(listOfSpecies)
        }
    }
}
// filter the list for search field
// when typing live data is going to observe first 2 characters matching speciesName
