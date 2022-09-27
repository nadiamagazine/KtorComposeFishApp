package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktorcomposeapp.ui.theme.KtorComposeAppTheme
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel

@Composable
internal fun SpeciesListScreen(
    viewModel: SpeciesViewModel
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        viewState.value?.let { FishList(listOfFish = it) }
    }
}

@Composable
fun Fish(
    speciesName: SpeciesName
) {
//    Card(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(8.dp, 4.dp)
//
//        shape = RoundedCornerShape(8.dp),
//        elevation = 4.dp
//    ) {
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
                .padding(8.dp)
                .size(85.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
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
    }
}
//}

@Composable
fun FishList(
    listOfFish: List<SpeciesName>
) {
    LazyColumn {
        itemsIndexed(items = listOfFish) { index, item ->
            Fish(speciesName = item)
        }
    }
}

@Composable
fun ProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            color = Color.Blue,
            strokeWidth = 10.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorComposeAppTheme {
        FishList(listOfFish = listOf())
    }
}
