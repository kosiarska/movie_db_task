package pl.tretowicz.moviedbdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import pl.tretowicz.moviedbdemo.NavArgs.MOVIE
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.Routes.MOVIE_LIST
import pl.tretowicz.moviedbdemo.Routes.SEARCH
import pl.tretowicz.moviedbdemo.ui.details.MovieDetails
import pl.tretowicz.moviedbdemo.ui.list.MovieList
import pl.tretowicz.moviedbdemo.ui.search.SearchQuery
import pl.tretowicz.moviedbdemo.ui.theme.MovieDbDemoTheme

object Routes {
  const val MOVIE_LIST = "movie_list"
  const val MOVIE_DETAILS = "movie_details/{movie}"
  const val SEARCH = "search"
}

object NavArgs {
  const val MOVIE = "movie"
  const val MOVIE_PARAM = "{movie}"
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MovieDbDemoTheme {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = MOVIE_LIST) {
          composable(MOVIE_LIST) { MovieList(navController) }
          composable(MOVIE_DETAILS, arguments = listOf(
            navArgument(name = MOVIE, builder = { type = NavType.LongType })
          )) {
            MovieDetails(navController)
          }
          composable(SEARCH) {
            SearchQuery(navController)
          }
        }
      }
    }
  }
}
