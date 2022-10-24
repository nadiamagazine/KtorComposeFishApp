package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.viewmodel.SpeciesDetailViewModel

// 1. Pass the DetailViewModel to this screen
//2. From this viewModel call the new endpoint (take in speciesName)
@Composable
fun SpeciesDetailScreen(
    speciesName: String,
    viewModel: SpeciesDetailViewModel
) {

   val viewState = viewModel.liveData.observeAsState()
//
//    viewState.value?.let {
//        SpeciesDetailedInformation(
//            speciesDetailedInfo = it.first()
//        )
//    }
    Text(text = speciesName)
}
@Composable
fun SpeciesDetailedInformation(
    speciesDetailedInfo: SpeciesDetailedInfo
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = speciesDetailedInfo.speciesName,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = speciesDetailedInfo.harvestType,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                speciesDetailedInfo.habitatImpacts?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(
                    text = speciesDetailedInfo.source,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = speciesDetailedInfo.biology,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = speciesDetailedInfo.healthBenefits,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
