package pl.tretowicz.moviedbdemo.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.LikeOrUnlikeMovie
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.SearchMovies
import timber.log.Timber
import javax.inject.Inject

@OptIn(FlowPreview::class)
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

  private val inputPhrase = MutableStateFlow("")

  private fun observePhraseChanges() {
    inputPhrase
      .map(::ensureLongEnough)
      .distinctUntilChanged()
      .debounce(::queryDebounceTime)
      .onEach(::downloadNewResults)
      .launchIn(viewModelScope)
  }

  private fun queryDebounceTime(phrase: String): Long =
    when (phrase == "") {
      true -> 0L
      false -> QUERY_DEBOUNCE_DURATION
    }

  private fun ensureLongEnough(phrase: String): String =
    when (phrase.length >= MIN_QUERY_LENGTH) {
      true -> phrase
      false -> ""
    }

  init {
    viewModelScope.launch {
      updateLikedMovies()
    }

    viewModelScope.launch {
      observeLikedMovies().collect {
        updateLikedMovies()
      }
    }

    observePhraseChanges()
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
    inputPhrase.update { query }
  }

  private fun downloadNewResults(query: String) {
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

  companion object {
    private const val MIN_QUERY_LENGTH = 1
    private const val QUERY_DEBOUNCE_DURATION = 300L
  }
}
