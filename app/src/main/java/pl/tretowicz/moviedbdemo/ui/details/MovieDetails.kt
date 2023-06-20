package pl.tretowicz.moviedbdemo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.size.Scale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedb.databinding.FragmentMovieDetailsBinding
import pl.tretowicz.moviedbdemo.ui.list.MovieListViewModel

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

  private var _binding: FragmentMovieDetailsBinding? = null
  private val binding get() = _binding!!

  private val viewModel: MovieDetailsViewModel by viewModels()

  private val likeMenuProvider = object : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
      menuInflater.inflate(R.menu.menu_details, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when (menuItem.itemId) {
        R.id.star -> {
          viewModel.toggleLike()
          true
        }

        else -> false
      }
    }
  }

  private val likedMenuProvider = object : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
      menuInflater.inflate(R.menu.menu_details_liked, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      return when (menuItem.itemId) {
        R.id.star -> {
          viewModel.toggleLike()
          true
        }

        else -> false
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycleScope.launch {
      viewModel.state.collect { state ->

        setMenu(state.isLiked)

        binding.apply {
          loading.isVisible = state.isLoading
          title.text = state.title
          description.text = state.overview.ifEmpty { getString(R.string.no_description) }
          image.load(MovieListViewModel.IMAGES_PATH + state.poster) {
            crossfade(250)
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
            scale(Scale.FILL)
          }
          releaseDate.text = getString(R.string.release_date, state.releaseDate)
          votesAverage.text = getString(R.string.votes_average, state.voteAverage)
        }
      }
    }
  }

  private fun setMenu(liked: Boolean) {
    val menuHost: MenuHost = requireActivity()
    menuHost.removeMenuProvider(likedMenuProvider)
    menuHost.removeMenuProvider(likeMenuProvider)
    menuHost.addMenuProvider(
      if (liked) likedMenuProvider else likeMenuProvider,
      viewLifecycleOwner,
      State.RESUMED
    )
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
