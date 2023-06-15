package pl.tretowicz.moviedbdemo.ui.list


data class MovieListState(
  val likedMovies: List<Long> = emptyList(),
) {
  companion object {
    fun empty() = MovieListState()
  }
}
