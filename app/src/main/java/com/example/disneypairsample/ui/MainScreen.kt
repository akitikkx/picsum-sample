package com.example.disneypairsample.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.disneypairsample.domain.PicsumPhotoItem

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val imageState by mainViewModel.uiState.collectAsState()

    Scaffold(modifier = modifier) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

            when (imageState) {
                is ImageUiState.Success -> {
                    ImageList((imageState as ImageUiState.Success).images)
                }

                is ImageUiState.Error -> {
                    ErrorScreen((imageState as ImageUiState.Error).message)
                }

                is ImageUiState.Loading -> {
                    LoadingScreen()
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ImageList(list: List<PicsumPhotoItem>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        items(list) { item ->
            Card(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.downloadUrl)
                        .build(),
                    contentDescription = item.author,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(text = item.id)
            }
        }
    }
}

@Composable
fun ErrorScreen(message: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = message)
    }
}