package pl.tretowicz.moviedbdemo.ui.search

import pl.tretowicz.moviedbdemo.domain.model.Movie

data class SearchQueryState(
  val predictions: List<String> = emptyList(),
  val movies: List<Movie> = emptyList(),
  val likedMovies: List<Long> = emptyList(),
) {
  companion object {
    fun empty() = SearchQueryState()
  }
}
