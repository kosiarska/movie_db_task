package pl.tretowicz.moviedbdemo.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.SearchMovies
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchQueryViewModel @Inject constructor(
  private val searchMovies: SearchMovies,
  private val likeOrUnlikeMovie: LikeOrUnlikeMovie,
  private val getLikedMovies: GetLikedMovies,
  private val observeLikedMovies: ObserveLikedMovies
) : ViewModel() {

  private val errorHandler = CoroutineExceptionHandler { _, throwable ->
    Timber.e(throwable)
    FirebaseCrashlytics.getInstance().recordException(throwable)
  }

  private val _state = MutableStateFlow(SearchQueryState.empty())

  internal val state: StateFlow<SearchQueryState> = _state

  init {
    viewModelScope.launch {
      updateLikedMovies()
    }

    viewModelScope.launch {
      observeLikedMovies().collect {
        updateLikedMovies()
      }
    }
  }

  private suspend fun updateLikedMovies() {
    _state.update {
      _state.value.copy(
        likedMovies = getLikedMovies()
      )
    }
  }

  fun search(query: String) {
    _state.update {
      _state.value.copy(
        query = query
      )
    }
    viewModelScope.launch(errorHandler) {
      val searchedMovies = searchMovies(query)
      _state.update {
        _state.value.copy(
          predictions = searchedMovies.map {
            it.title
          }.toSet().toList(),
          movies = searchedMovies
        )
      }
    }
  }

  fun toggleLike(id: Long) {
    viewModelScope.launch {
      likeOrUnlikeMovie(id)
    }
  }
}
