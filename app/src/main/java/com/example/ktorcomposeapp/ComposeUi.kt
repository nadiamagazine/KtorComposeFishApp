package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ktorcomposeapp.ui.theme.KtorComposeAppTheme
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel

@Composable
internal fun SpeciesListScreen(
    viewModel: SpeciesViewModel
) {
    val viewState = viewModel.liveData.observeAsState()

    viewState.value?.let { FishList(listOfFish = it) }
}

@Composable
fun Fish(
    speciesName: SpeciesName
) {
    Card(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            androidx.compose.ui.Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Text(
                text = speciesName.speciesName,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FishList(
    listOfFish: List<SpeciesName>) {
    LazyColumn {
        itemsIndexed(items = listOfFish) { index, item ->
            Fish(speciesName = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorComposeAppTheme {
        FishList(listOfFish = listOf())
    }
}
