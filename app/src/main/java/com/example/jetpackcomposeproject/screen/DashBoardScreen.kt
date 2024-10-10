package com.example.jetpackcomposeproject.screen

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.jetpackcomposeproject.model.MoviesList
import com.example.jetpackcomposeproject.model.MoviesResponse
import com.example.jetpackcomposeproject.utils.NetworkResult
import com.example.jetpackcomposeproject.viewModel.MainViewModel
import com.google.gson.Gson

@Composable
fun DashBoardField(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val isLoading = rememberSaveable { mutableStateOf(false) }
    val popularMoviesResponse by viewModel.PopularMovieResponse.observeAsState()
    var moviesList by remember { mutableStateOf<List<MoviesList>>(emptyList()) }


    DisposableEffect(key1 = Unit) {
        viewModel.getPopularMovies()

        onDispose {
            viewModel.PopularMovieResponse.value = null
        }
    }

    LaunchedEffect(key1 = popularMoviesResponse) {
        popularMoviesResponse?.let { apiResult ->
            when (apiResult) {
                is NetworkResult.Success -> {
                    isLoading.value = false
                    if (!apiResult.networkData.isNullOrEmpty()) {
                        moviesList = apiResult.networkData
                        Log.d("popular_movies_data", Gson().toJson(moviesList))
                    }
                }
                is NetworkResult.Error -> {
                    isLoading.value = false
                }
                is NetworkResult.Loading -> {
                    isLoading.value = true
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        MainLayout(popularMoviesRes = moviesList, isLoading = isLoading.value)
    }
    


//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(0.dp, 50.dp, 0.dp, 0.dp),
//        contentAlignment = Alignment.TopCenter
//    ) {
//        Card(
//            colors = CardDefaults.cardColors(
//                containerColor = MaterialTheme.colorScheme.surfaceVariant,
//            ),
//            modifier = Modifier
//                .size(width = 320.dp, height = 400.dp)
//        ) {
//            Text(
//                text = "Filled",
//                modifier = Modifier
//                    .padding(16.dp),
//                textAlign = TextAlign.Center,
//            )
//        }
//    }


}

@Composable
fun MainLayout(popularMoviesRes: List<MoviesList>, isLoading: Boolean) {
    if (isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        ShowPopularMovies(popularMoviesRes = popularMoviesRes)
    }

}

@Composable
fun ShowPopularMovies(
    popularMoviesRes: List<MoviesList>?
) {
    LazyColumn(
        modifier = Modifier
    ) {
        popularMoviesRes?.let { movies ->
            items(movies) {
                PopularMoviesUI(movies = it)
            }
        }
    }
}

@Composable
fun PopularMoviesUI(modifier: Modifier = Modifier, movies: MoviesList) {
    Card(
        onClick = { },
        modifier = modifier.padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 4.dp,

            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                onClick = { },
                modifier = modifier.padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,

                    )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500"+movies.backdropPath,
                    contentDescription = null,
                    modifier = Modifier

                )
            }

            Spacer(modifier = Modifier.height(0.dp))
            Text(text = movies.title)
            Spacer(modifier = Modifier.height(0.dp))
            Text(text = movies.originalLanguage)
            Spacer(modifier = Modifier.height(0.dp))
            Text(text = movies.originalTitle)
            Spacer(modifier = Modifier.height(0.dp))
            Text(text = movies.releaseDate)
            Spacer(modifier = Modifier.height(0.dp))
            Text(text = movies.overview)
            Spacer(modifier = Modifier.height(0.dp))
            Card(
                onClick = { },
                modifier = modifier.padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,

                    )
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500"+movies.posterPath,
                    contentDescription = null,
                    modifier = Modifier

                )
            }



        }

    }

}

