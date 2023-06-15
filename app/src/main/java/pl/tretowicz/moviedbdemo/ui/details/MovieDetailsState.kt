package pl.tretowicz.moviedbdemo.ui.details

data class MovieDetailsState(
  val movieId: Long = 0,
  val title: String = "",
  val poster: String = "",
  val overview: String = "",
  val releaseDate: String = "",
  val voteAverage: String = "",
  val isLiked: Boolean = false
) {
  companion object {
    fun empty() = MovieDetailsState()
  }
}
