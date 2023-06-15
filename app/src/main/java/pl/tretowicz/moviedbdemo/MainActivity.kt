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
import pl.tretowicz.moviedbdemo.ui.details.MovieDetails
import pl.tretowicz.moviedbdemo.ui.list.MovieList
import pl.tretowicz.moviedbdemo.ui.search.SearchQuery
import pl.tretowicz.moviedbdemo.ui.theme.MovieDbDemoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MovieDbDemoTheme {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "movie_list") {
          composable("movie_list") { MovieList(navController) }
          composable("movie_details/{movie}", arguments = listOf(
            navArgument(name = "movie", builder = { type = NavType.LongType })
          )) {
            MovieDetails(navController)
          }
          composable("search") {
            SearchQuery(navController)
          }
        }
      }
    }
  }
}
