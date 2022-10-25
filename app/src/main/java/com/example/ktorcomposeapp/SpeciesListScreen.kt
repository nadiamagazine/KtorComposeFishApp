package com.example.ktorcomposeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktorcomposeapp.model.SpeciesNameAndImage
import com.example.ktorcomposeapp.viewmodel.SpeciesViewModel

@Composable
internal fun SpeciesListScreen(
    navController: NavController,
    viewModel: SpeciesViewModel
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_encyclopedia_fishing),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchField {
                viewModel.filterListOfSpecies(it)
            }
            viewState.value?.let {
                FishList(
                    navController = navController,
                    listOfFish = it
                )
            }
        }
    }
}

@Composable
fun Fish(
    navController: NavController,
    speciesName: SpeciesNameAndImage
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 4.dp)
            .clickable {
                navController.navigate(
                    "SpeciesDetailScreen/${speciesName.speciesName}"
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
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

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = speciesName.speciesName,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = speciesName.scientificName,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}


@Composable
fun FishList(
    navController: NavController,
    listOfFish: List<SpeciesNameAndImage>
) {
    LazyColumn {
        itemsIndexed(items = listOfFish) { index, item ->
            Fish(
                navController = navController,
                speciesName = item
            )
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

@Composable
fun SearchField(
    onSearch: (String) -> Unit = {}
) {
    val state = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    )
    {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
                onSearch(value)
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape),
            textStyle = TextStyle(
                color = Color.Black, fontSize = 20.sp
            ),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(25.dp)
                )
            },
            trailingIcon = {
                if (state.value != "")
                    IconButton(onClick = {
                        state.value = ""
                    }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .size(25.dp)
                        )
                    }
            },
            singleLine = true
        )
    }
}
