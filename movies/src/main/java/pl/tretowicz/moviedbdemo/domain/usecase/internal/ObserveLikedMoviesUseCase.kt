package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import javax.inject.Inject

@Reusable
internal class ObserveLikedMoviesUseCase @Inject constructor(
  private val likedMoviesPersistence: LikedMoviesPersistence
) : ObserveLikedMovies {

  override fun invoke(): Flow<List<Long>> =
    likedMoviesPersistence.observeLikedMovies()
      .map { it ->
        it.map { it.id }
      }

}
