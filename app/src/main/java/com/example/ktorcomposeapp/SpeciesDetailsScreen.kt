package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktorcomposeapp.model.SpeciesResponse
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel

@Composable
fun SpeciesDetailScreen(
    viewModel: SpeciesViewModel
) {

    val viewState = viewModel.liveData.observeAsState()

    viewState.value?.let {
        SpeciesDetailedInformation(
            speciesName = it
        )
    }
}

@Composable
fun SpeciesDetailedInformation(
    speciesName: SpeciesResponse
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(speciesName.speciesIllustrationPhoto?.src)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(4.dp)
            ) {
                Text(
                    text = speciesName.speciesName,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = speciesName.harvestType,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                speciesName.habitatImpacts?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(
                    text = speciesName.source,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = speciesName.biology,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = speciesName.healthBenefits,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
