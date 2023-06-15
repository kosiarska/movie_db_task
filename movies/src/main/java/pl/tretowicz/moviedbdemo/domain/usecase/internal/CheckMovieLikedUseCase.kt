package pl.tretowicz.moviedbdemo.domain.usecase.internal

import dagger.Reusable
import pl.tretowicz.moviedbdemo.domain.usecase.CheckMovieLiked
import pl.tretowicz.moviedbdemo.persistence.LikedMoviesPersistence
import javax.inject.Inject

@Reusable
internal class CheckMovieLikedUseCase @Inject constructor(
  private val moviesPersistence: LikedMoviesPersistence
) : CheckMovieLiked {

  override suspend fun invoke(id: Long): Boolean =
    moviesPersistence.findLikedMovie(id) != null
}
