package pl.tretowicz.moviedbdemo.ui.list

import dagger.Reusable
import javax.inject.Inject

@Reusable
class MovieListStateFactory @Inject constructor() {

  internal fun create(
    likedMovies: List<Long>
  ): MovieListState =
    MovieListState(
      likedMovies = likedMovies
    )
}
