package com.example.ktorcomposeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Column {
            SearchField(state = remember { mutableStateOf(TextFieldValue("")) })
            viewState.value?.let { FishList(listOfFish = it) }
        }
    }
}

@Composable
fun Fish(
    speciesName: SpeciesName
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 4.dp),
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
            }
        }
    }
}

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

@Composable
fun SearchField(
    state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = Color.White, fontSize = 20.sp
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
            if (state.value != TextFieldValue(""))
                IconButton(onClick = {
                    state.value = TextFieldValue("")
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
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = colorResource(id = R.color.black),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorComposeAppTheme {
        FishList(listOfFish = listOf())
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchField(textState)
}
