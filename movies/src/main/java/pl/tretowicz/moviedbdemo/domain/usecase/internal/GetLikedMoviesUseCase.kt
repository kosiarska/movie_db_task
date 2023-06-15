package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.tretowicz.moviedbdemo.domain.usecase.GetLikedMovies
import pl.tretowicz.moviedbdemo.domain.usecase.ObserveLikedMovies
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import javax.inject.Inject

@Reusable
internal class GetLikedMoviesUseCase @Inject constructor(
  private val likedMoviesPersistence: LikedMoviesPersistence
) : GetLikedMovies {

  override suspend fun invoke(): List<Long> =
    likedMoviesPersistence.getLikedMovies()
      .map {
        it.id
      }
}
