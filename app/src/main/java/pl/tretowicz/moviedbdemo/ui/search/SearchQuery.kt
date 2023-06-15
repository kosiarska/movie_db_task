package pl.tretowicz.moviedbdemo.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedbdemo.NavArgs
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.ui.list.MovieItem
import pl.tretowicz.moviedbdemo.ui.views.AutoCompleteUI

@Composable
fun SearchQuery(navController: NavHostController) {

  val viewModel = hiltViewModel<SearchQueryViewModel>()

  val state by viewModel.state.collectAsState()

  Column {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        modifier = Modifier
          .padding(horizontal = 10.dp)
          .clickable {
            navController.popBackStack()
          },
        imageVector = Filled.ArrowBack,
        contentDescription = null
      )
      AutoCompleteUI(
        modifier = Modifier.fillMaxWidth(),
        query = state.query,
        onQueryChanged = {
          viewModel.search(it)
        },
        queryLabel = stringResource(id = R.string.search),
        predictions = state.predictions,
        itemContent = {
          Text(text = it)
        },
        onItemClick = {
          viewModel.search(it)
        }
      )
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
      items(state.movies) { movie ->
        MovieItem(
          movie = movie,
          toggleLike = {
            viewModel.toggleLike(it)
          },
          goToDetails = {
            navController.navigate(MOVIE_DETAILS.replace(NavArgs.MOVIE_PARAM, it.id.toString()))
          },
          likedMovies = state.likedMovies
        )
      }
    }
  }
}

