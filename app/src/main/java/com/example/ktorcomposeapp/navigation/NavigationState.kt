package com.example.ktorcomposeapp.navigation

import java.util.UUID

sealed class NavigationState {

    object Navigation : NavigationState()

    data class NavigateToDetailsScreen(
        val route: String,
        val id: String = UUID.randomUUID().toString()
    ) : NavigationState()
}
