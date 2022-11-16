package com.example.ktorcomposeapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ktorcomposeapp.model.SpeciesNameAndImage
import com.example.ktorcomposeapp.navigation.RouteNavigator
import com.example.ktorcomposeapp.service.KtorService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpeciesViewModel(
    routeNavigator: RouteNavigator
): ViewModel(), RouteNavigator by routeNavigator {

    private var cachedSpeciesList = listOf<SpeciesNameAndImage>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    private var _liveData = MutableLiveData<List<SpeciesNameAndImage>>()
    val liveData: LiveData<List<SpeciesNameAndImage>> = _liveData

    init {
        getSpeciesName()
    }

    private fun getSpeciesName() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfSpecies = KtorService.create().getListOfSpeciesNames()
            _liveData.postValue(listOfSpecies)
        }
    }

    fun filterListOfSpecies(query: String) {
       val listToSearch = if (isSearchStarting) {
           _liveData.value
       } else {
           cachedSpeciesList
       }
        viewModelScope.launch(Dispatchers.Main) {
            if (query.isEmpty()) {
                _liveData.value = cachedSpeciesList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val result = listToSearch?.filter {
                it.speciesName.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                cachedSpeciesList = _liveData.value!!
                isSearchStarting = false
            }
            _liveData.value = result
            isSearching.value = true
        }
    }

    fun onFishClicked() {
        navigateToDetailsScreen("")
    }

    companion object {
        fun factory(routeNavigator: RouteNavigator): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    SpeciesViewModel(routeNavigator)
                }
            }
    }
}
