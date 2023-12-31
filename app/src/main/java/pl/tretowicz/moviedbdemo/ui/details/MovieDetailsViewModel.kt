package pl.tretowicz.moviedbdemo.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.tretowicz.moviedbdemo.domain.model.Movie
import pl.tretowicz.moviedbdemo.domain.usecase.CheckMovieLiked
import pl.tretowicz.moviedbdemo.domain.usecase.GetMovieDetails
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.network.paging.MoviesRepository
import timber.log.Timber
import timber.log.Timber.Forest
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
  private val getMovieDetails: GetMovieDetails,
  private val likeOrUnlikeMovie: LikeOrUnlikeMovie,
  private val checkMovieLiked: CheckMovieLiked,
  private val observeLikedMovies: ObserveLikedMovies
) : ViewModel() {

  private val errorHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable)
    FirebaseCrashlytics.getInstance().recordException(throwable)
  }

  private val _state = MutableStateFlow(MovieDetailsState.empty())

  internal val state: StateFlow<MovieDetailsState> = _state

  init {
    val movieId = savedStateHandle.get<Long>("movie") ?: 0

    viewModelScope.launch(errorHandler) {

      val movieDetails = getMovieDetails(movieId)

      observeLikedMovies().collect {
        _state.update {
          _state.value.copy(
            movieId = movieDetails.id,
            title = movieDetails.title,
            poster = movieDetails.posterPath.orEmpty(),
            overview = movieDetails.overview,
            releaseDate = movieDetails.releaseDate,
            voteAverage = movieDetails.voteAverage.toString(),
            isLiked = checkMovieLiked(movieDetails.id)
          )
        }
      }
    }
  }

  fun toggleLike() {
    viewModelScope.launch {
      likeOrUnlikeMovie(_state.value.movieId)
    }
  }
}
