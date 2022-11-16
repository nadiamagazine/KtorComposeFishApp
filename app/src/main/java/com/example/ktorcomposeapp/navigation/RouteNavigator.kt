package com.example.ktorcomposeapp.navigation

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateToDetailsScreen(route: String)

    var navigationState: StateFlow<NavigationState>
}

class MyRouteNavigator : RouteNavigator {

    override var navigationState: StateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Navigation)


    override fun onNavigated(state: NavigationState) {
        navigationState
    }

    override fun navigateToDetailsScreen(route: String) =
        navigate(NavigationState.NavigateToDetailsScreen(route))

    @VisibleForTesting
    private fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}


