package pl.tretowicz.moviedbdemo.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.network.paging.MoviesRepository
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
  private val repository: MoviesRepository,
  private val viewState: MovieListStateFactory,
  observeLikedMovies: ObserveLikedMovies,
  private val likeOrUnlikeMovie: LikeOrUnlikeMovie
) : ViewModel() {

  internal val state: StateFlow<MovieListState> =
    observeLikedMovies()
      .map { createState(it) }
      .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = MovieListState.empty()
      )

  private fun createState(
    likedMovies: List<Long>
  ): MovieListState =
    viewState.create(
      likedMovies = likedMovies
    )

  fun getMovies(): Flow<PagingData<Movie>> =
    repository.getMovies().cachedIn(viewModelScope)

  fun toggleLike(id: Long) {
    viewModelScope.launch {
      likeOrUnlikeMovie(id)
    }
  }

  companion object {
    const val IMAGES_PATH = "https://image.tmdb.org/t/p/w500"
  }
}
