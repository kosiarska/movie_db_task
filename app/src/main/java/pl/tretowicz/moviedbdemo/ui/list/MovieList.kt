package pl.tretowicz.moviedbdemo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedb.databinding.FragmentMovieListBinding
import pl.tretowicz.moviedbdemo.NavArgs.MOVIE_PARAM
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.Routes.SEARCH
import pl.tretowicz.moviedbdemo.ui.list.adapter.MovieListAdapter
import pl.tretowicz.moviedbdemo.utils.VerticalSpaceItemDecoration
import pl.tretowicz.moviedbdemo.utils.dpToPx

@AndroidEntryPoint
class MovieListFragment : Fragment() {

  private var _binding: FragmentMovieListBinding? = null
  private val binding get() = _binding!!

  private val viewModel: MovieListViewModel by viewModels()

  private lateinit var pagingAdapter: MovieListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentMovieListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setAdapter()

    lifecycleScope.launch {
      viewModel.state.collect {
        pagingAdapter.setLikedMovies(it.likedMovies)
      }
    }

    lifecycleScope.launch {
      viewModel.getMovies().collectLatest { pagingData ->
        pagingAdapter.submitData(pagingData)
      }
    }

    setMenu()
  }

  private fun setMenu() {
    val menuHost: MenuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_movie_list, menu)
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
          R.id.search -> {
            findNavController().navigate(SEARCH)
            true
          }

          else -> false
        }
      }
    }, viewLifecycleOwner, State.RESUMED)
  }

  private fun setAdapter() {
    pagingAdapter = MovieListAdapter(
      onItemClick = {
        findNavController().navigate(
          MOVIE_DETAILS.replace(MOVIE_PARAM, it?.id.toString())
        )
      },
      onToggleLike = {
        viewModel.toggleLike(it?.id ?: 0)
      }
    )

    binding.list.addItemDecoration(
      VerticalSpaceItemDecoration(requireContext().dpToPx(10f).toInt()))

    binding.list.adapter = pagingAdapter
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding.list.adapter = null
    _binding = null
  }
}
