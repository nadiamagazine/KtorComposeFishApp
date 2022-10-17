package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel

@Composable
fun SpeciesDetailScreen(
    speciesName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    speciesImageSize: Dp = 200.dp,
    viewModel: SpeciesViewModel
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp)
    ) {

    }
}
