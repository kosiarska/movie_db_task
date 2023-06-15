package pl.tretowicz.moviedbdemo.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedbdemo.NavArgs.MOVIE_PARAM
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.Routes.SEARCH
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.ui.list.MovieListViewModel.Companion.IMAGES_PATH
import pl.tretowicz.moviedbdemo.utils.rememberForeverLazyListState

@Composable
fun MovieList(navController: NavHostController) {

  val viewModel = hiltViewModel<MovieListViewModel>()

  val state by viewModel.state.collectAsState()
  val movies = viewModel.getMovies().collectAsLazyPagingItems()

  MovieListContent(
    state = state,
    movies = movies,
    toggleLike = {
      viewModel.toggleLike(it)
    },
    goToDetails = {
      navController.navigate(MOVIE_DETAILS.replace(MOVIE_PARAM, it.id.toString()))
    },
    goToSearch = {
      navController.navigate(SEARCH)
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListContent(
  state: MovieListState,
  movies: LazyPagingItems<Movie>,
  toggleLike: (Long) -> Unit,
  goToDetails: (Movie) -> Unit,
  goToSearch: () -> Unit
) {

  Column {
    TopAppBar(
      colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
      title = {
        Text(
          text = stringResource(id = R.string.movie_list),
          color = Color.White
        )
      },
      navigationIcon = {
      },
      actions = {
        Icon(
          modifier = Modifier
            .padding(horizontal = 15.dp)
            .clickable {
              goToSearch()
            },
          imageVector = Filled.Search,
          contentDescription = null,
          tint = Color.White
        )
      }
    )

    if (movies.itemCount > 0) {
      LazyColumn(
        modifier = Modifier.background(Color.LightGray),
        state = rememberForeverLazyListState(key = "movies")
      ) {
        items(
          count = movies.itemCount,
          key = movies.itemKey(),
          contentType = movies.itemContentType(null)
        ) { index ->
          val movie = movies[index]
          movie?.let {
            MovieItem(
              movie = it,
              likedMovies = state.likedMovies,
              goToDetails = goToDetails,
              toggleLike = toggleLike
            )
          }
        }

        when (movies.loadState.append) {
          is LoadState.Error -> {
          }

          is LoadState.Loading -> {
            item {
              Box(
                modifier = Modifier
                  .fillMaxWidth(),
                contentAlignment = Alignment.Center
              ) {
                CircularProgressIndicator(color = Color.Black)
              }
            }
          }

          else -> {}
        }
      }
    } else {
      Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
      ) {
        CircularProgressIndicator()
      }
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MovieItem(
  movie: Movie,
  likedMovies: List<Long>,
  goToDetails: (Movie) -> Unit,
  toggleLike: (Long) -> Unit,
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .defaultMinSize(minHeight = 200.dp)
      .padding(horizontal = 15.dp, vertical = 10.dp),
    colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
    onClick = {
      goToDetails(movie)
    }
  ) {

    Column(modifier = Modifier.padding(all = 10.dp)) {

      Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
          modifier = Modifier.size(100.dp),
          model = Builder(LocalContext.current)
            .data(IMAGES_PATH + movie.posterPath.orEmpty())
            .crossfade(true)
            .build(),
          placeholder = painterResource(R.drawable.ic_launcher_background),
          contentDescription = null,
          contentScale = ContentScale.Crop
        )

        Text(
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 10.dp),
          text = movie.title,
          color = Color.Black,
          fontWeight = FontWeight.Medium
        )

        Icon(
          modifier = Modifier.clickable {
            val id = movie.id
            toggleLike(id)
          },
          imageVector = Filled.Star,
          contentDescription = null,
          tint = if (likedMovies.contains(movie.id)) Color.Green else Color.LightGray
        )
      }

      Text(
        modifier = Modifier.padding(top = 10.dp),
        text = movie.overview
          .ifEmpty { stringResource(id = R.string.no_description) },
        color = Color.DarkGray,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}
