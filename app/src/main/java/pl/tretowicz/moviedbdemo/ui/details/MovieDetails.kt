package pl.tretowicz.moviedbdemo.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedbdemo.ui.list.MovieListViewModel

@Composable
fun MovieDetails(
  navController: NavHostController
) {
  val viewModel = hiltViewModel<MovieDetailsViewModel>()

  val state by viewModel.state.collectAsState()

  MovieDetailsContent(
    state = state,
    navController = navController,
    toggleLike = {
      viewModel.toggleLike()
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsContent(
  state: MovieDetailsState,
  navController: NavHostController,
  toggleLike: () -> Unit
) {
  Column(modifier = Modifier
    .fillMaxSize()
    .background(Color.White)) {

    TopAppBar(
      colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
      title = {
        Text(
          text = stringResource(id = R.string.details_title),
          color = Color.White
        )
      },
      navigationIcon = {
        Icon(
          modifier = Modifier
            .padding(horizontal = 10.dp)
            .clickable {
              navController.popBackStack()
            },
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = null,
          tint = Color.White
        )
      },
      actions = {
        Icon(
          modifier = Modifier
            .padding(horizontal = 15.dp)
            .clickable {
              toggleLike()
            },
          imageVector = Icons.Filled.Star,
          contentDescription = null,
          tint = if (state.isLiked) Color.Green else Color.White
        )
      }
    )

    AsyncImage(
      modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
      model = Builder(LocalContext.current)
        .data(MovieListViewModel.IMAGES_PATH + state.poster)
        .crossfade(true)
        .build(),
      placeholder = painterResource(R.drawable.ic_launcher_background),
      contentDescription = null,
      contentScale = ContentScale.Crop
    )

    Text(
      modifier = Modifier
        .padding(horizontal = 15.dp)
        .padding(top = 10.dp),
      text = state.title,
      color = Color.Black,
      fontWeight = FontWeight.Medium
    )

    Text(
      modifier = Modifier
        .padding(top = 10.dp)
        .padding(horizontal = 15.dp),
      text = state.overview
        .ifEmpty { stringResource(id = R.string.no_description) },
      color = Color.DarkGray,
    )

    Text(
      modifier = Modifier
        .padding(top = 20.dp)
        .padding(horizontal = 15.dp),
      text = stringResource(id = R.string.release_date, state.releaseDate),
      color = Color.Black,
      fontWeight = FontWeight.Bold
    )

    Text(
      modifier = Modifier
        .padding(top = 10.dp)
        .padding(horizontal = 15.dp),
      text = stringResource(id = R.string.votes_average, state.voteAverage),
      color = Color.Black,
      fontWeight = FontWeight.Bold
    )
  }
}

