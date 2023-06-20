package pl.tretowicz.moviedbdemo.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import pl.tretowicz.moviedb.R
import pl.tretowicz.moviedb.databinding.FragmentSearchQueryBinding
import pl.tretowicz.moviedbdemo.NavArgs.MOVIE_PARAM
import pl.tretowicz.moviedbdemo.Routes.MOVIE_DETAILS
import pl.tretowicz.moviedbdemo.ui.list.adapter.MovieListAdapter
import pl.tretowicz.moviedbdemo.utils.VerticalSpaceItemDecoration
import pl.tretowicz.moviedbdemo.utils.dpToPx

@AndroidEntryPoint
class SearchQueryFragment : Fragment() {

  private var _binding: FragmentSearchQueryBinding? = null
  private val binding get() = _binding!!

  private val viewModel: SearchQueryViewModel by viewModels()
  private lateinit var pagingAdapter: MovieListAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentSearchQueryBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setMenu()
    setAdapter()

    lifecycleScope.launch {
      viewModel.state.collect {
        pagingAdapter.submitData(PagingData.from(it.movies))
        pagingAdapter.setLikedMovies(it.likedMovies)
      }
    }
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

    binding.list.apply {
      addItemDecoration(
        VerticalSpaceItemDecoration(requireContext().dpToPx(10f).toInt()))
      adapter = pagingAdapter
    }
  }

  private fun setMenu() {
    val menuHost: MenuHost = requireActivity()
    menuHost.addMenuProvider(object : MenuProvider {
      override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_search, menu)

        val item = menu.findItem(R.id.search)

        val search = item.actionView as SearchView
        search.setOnQueryTextListener(object : OnQueryTextListener {
          override fun onQueryTextSubmit(query: String?): Boolean {
            viewModel.search(query.orEmpty())
            return true
          }

          override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.search(newText.orEmpty())
            return true
          }
        })
      }

      override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
          R.id.search -> {
            true
          }

          else -> false
        }
      }
    }, viewLifecycleOwner, State.RESUMED)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding.list.adapter = null
    _binding = null
  }
}
