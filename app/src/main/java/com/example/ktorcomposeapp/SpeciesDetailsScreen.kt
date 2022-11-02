package com.example.ktorcomposeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ktorcomposeapp.model.SpeciesDetailedInfo
import com.example.ktorcomposeapp.viewmodel.SpeciesDetailViewModel

@Composable
fun SpeciesDetailScreen(
    speciesName: String,
    viewModel: SpeciesDetailViewModel = viewModel(
        factory =
        SpeciesDetailViewModel.factory(speciesName)
    )
) {

    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
            viewState.value?.let {
                if (it.isNotEmpty()) {
                    SpeciesDetailedInformation(
                        speciesDetailedInfo = it.first()
                    )
                } else {
                    ErrorHandlingMessage()
                }
            }
    }
}


@Composable
fun SpeciesDetailedInformation(
    speciesDetailedInfo: SpeciesDetailedInfo
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(bottom = 16.dp)

    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Text(
                text = speciesDetailedInfo.speciesName,
                style = MaterialTheme.typography.h3,
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
                    style = MaterialTheme.typography.subtitle1,
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
                style = MaterialTheme.typography.h5,
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
