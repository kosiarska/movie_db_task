package pl.tretowicz.moviedbdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import dagger.hilt.android.AndroidEntryPoint
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedb.R.drawable
import pl.tretowicz.moviedb.R.string
import pl.tretowicz.moviedb.databinding.ActivityMainBinding
import pl.tretowicz.moviedbdemo.NavArgs.MOVIE
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.Routes.MOVIE_LIST
import pl.tretowicz.moviedbdemo.Routes.SEARCH
import pl.tretowicz.moviedbdemo.ui.details.MovieDetailsFragment
import pl.tretowicz.moviedbdemo.ui.list.MovieListFragment
import pl.tretowicz.moviedbdemo.ui.search.SearchQueryFragment
import pl.tretowicz.moviedbdemo.utils.hideSoftKeyboard

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
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setSupportActionBar(binding.toolbar)

    val navController = getNavController()

    listenToFragmentChange()

    navController.graph = navController.createGraph(
      startDestination = MOVIE_LIST
    ) {
      fragment<MovieListFragment>(MOVIE_LIST)

      fragment<MovieDetailsFragment>(MOVIE_DETAILS) {
        argument(MOVIE) {
          type = NavType.LongType
        }
      }

      fragment<SearchQueryFragment>(SEARCH)
    }

    binding.toolbar.setNavigationOnClickListener {
      navController.popBackStack()
    }
  }

  private fun listenToFragmentChange() {
    getNavController().addOnDestinationChangedListener { _, destination, _ ->

      hideSoftKeyboard()

      val titleMap = hashMapOf<String, String>().apply {
        put(SEARCH, getString(string.search))
        put(MOVIE_DETAILS, getString(string.details_title))
        put(MOVIE_LIST, getString(string.movie_list))
      }
      binding.toolbar.title = titleMap[destination.route]

      when (destination.route) {
        SEARCH -> {
          binding.toolbar.setNavigationIcon(drawable.baseline_arrow_back_24)
        }

        MOVIE_DETAILS -> {
          binding.toolbar.setNavigationIcon(drawable.baseline_arrow_back_24)
        }

        else -> {
          binding.toolbar.navigationIcon = null
        }
      }
    }
  }

  private fun getNavController(): NavController {
    val fragment = binding.navHost.getFragment<NavHostFragment>()
    return fragment.navController
  }
}
